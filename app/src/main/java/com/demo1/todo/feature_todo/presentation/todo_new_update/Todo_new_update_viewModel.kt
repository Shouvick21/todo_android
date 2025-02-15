package com.demo1.todo.feature_todo.presentation.todo_new_update

import android.provider.Settings
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo1.todo.core.utils.newUpdateStrings
import com.demo1.todo.feature_todo.data.di.IoDipatcher
import com.demo1.todo.feature_todo.domain.usecase.TodoUsecases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Todo_new_update_viewModel @Inject constructor(
    private val todoUsecases: TodoUsecases,
    @IoDipatcher private val dispatcher: CoroutineDispatcher,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(Todo_new_update_state())
    val state : State<Todo_new_update_state> = _state

    private val errorHandler  = CoroutineExceptionHandler { _, e ->
        e.printStackTrace()
        _state.value = _state.value.copy(
            error = e.message,
            isLoading = false
        )
    }
private var currentTodoId : Int? = null

    private val _eventFlow = MutableSharedFlow<UiEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent{
        data class showSnackBar(val message : String) : UiEvent()
        object saveTodo : UiEvent()
        object Back : UiEvent()
    }
    init {
        savedStateHandle.get<Int>("todoId")?.let { id ->
            if(id != -1){
                viewModelScope.launch {
                    todoUsecases.getSingleTodoByIdUsecase(id)?.also {todo ->
                        currentTodoId = id
                        _state.value = _state.value.copy(
                            todo = todo,
                            isLoading = false,
                            isTitleHintVisible = todo.title.isBlank(),
                            isDescriptionHintVisible = todo.desciption.isEmpty()
                        )
                    }
                }

            }
            else{
                _state.value = _state.value.copy(
                    isLoading = false,
                )
            }
        }
    }


    fun onEvent(event: TodoNewUpdateEvent){
    when(event){
        TodoNewUpdateEvent.Back -> {
            viewModelScope.launch(dispatcher + errorHandler) {
                _eventFlow.emit(UiEvent.Back)
            }
        }
        is TodoNewUpdateEvent.ChangedDescriptionFocus -> {
            val shouldDescriptionHintBeVisible = !event.focusState.isFocused && _state.value.todo.desciption.isBlank()
            _state.value = _state.value.copy(
                isDescriptionHintVisible = shouldDescriptionHintBeVisible
            )
        }
        is TodoNewUpdateEvent.ChangedTitleFocus -> {
            val shouldTitleHintBeVisible = !event.focusState.isFocused && _state.value.todo.desciption.isBlank()
            _state.value = _state.value.copy(
                isDescriptionHintVisible = shouldTitleHintBeVisible
            )
        }
        TodoNewUpdateEvent.Delete -> {

        }
        is TodoNewUpdateEvent.EnteredDescription -> {
            _state.value = _state.value.copy(
                todo = _state.value.todo.copy(
                    desciption = event.value
                )
            )

        }
        is TodoNewUpdateEvent.EnteredTitle -> {
            _state.value = _state.value.copy(
                todo = _state.value.todo.copy(
                    title = event.value
                )
            )
        }
        TodoNewUpdateEvent.ToggleCompleted -> {

        }
        TodoNewUpdateEvent.ToogleArchived -> {

        }
        TodoNewUpdateEvent.saveTodo -> {
            viewModelScope.launch(dispatcher + errorHandler) {
                try {
                    if (currentTodoId != null){
                        todoUsecases.updateTodoItemUsecase(_state.value.todo)
                    }else{
                        todoUsecases.addTodoItemUsecase(
                            _state.value.todo.copy(
                                timeStamp = System.currentTimeMillis(),
                                id= null
                            )
                        )
                    }
                    _eventFlow.emit()

                }
                catch (e : Exception){
                    _eventFlow.emit(
                        UiEvent.showSnackBar(
                            message = e.message ?: newUpdateStrings.SAVE_ERROR
                        )
                    )
                }
            }

        }
    }
    }



}