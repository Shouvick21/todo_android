package com.demo1.todo.feature_todo.presentation.todo_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo1.todo.core.utils.Todo_List_String_Resource
import com.demo1.todo.feature_todo.data.di.IoDipatcher
import com.demo1.todo.feature_todo.domain.model.TodoModel
import com.demo1.todo.feature_todo.domain.usecase.TodoUseCaseResult
import com.demo1.todo.feature_todo.domain.usecase.TodoUsecases
import com.demo1.todo.feature_todo.domain.utils.TodoItemsOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Todo_list_viewModel @Inject constructor(
    private val todoUsecases: TodoUsecases,
    @IoDipatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _state = mutableStateOf(Todo_list_state())
    val state : State<Todo_list_state> = _state

    private var undoTodoItem : TodoModel? = null
    private var getTodoItemsJob : Job? = null
    private val errorHandler = CoroutineExceptionHandler{_ , e->
        e.printStackTrace()
        _state.value = _state.value.copy(
            error = e.message,
            loading = false
        )
    }

    fun onEvent(event: TodoListEvent){
        when(event){
            is TodoListEvent.Delete -> {
                viewModelScope.launch(dispatcher + errorHandler) {
            todoUsecases.deleteTodoItemUsecase(event.todo)
                    getTodoItems()
                    undoTodoItem = event.todo
                }
            }
            is TodoListEvent.Sort -> {
                if (
                    _state.value.todoItemsOrder::class == event.todoItemsOrder::class &&
                    _state.value.todoItemsOrder.showArchived == event.todoItemsOrder.showArchived &&
                    _state.value.todoItemsOrder.sortingDirection == event.todoItemsOrder.sortingDirection
                    ){
                        return
                }
                _state.value = _state.value.copy(
                    todoItemsOrder = event.todoItemsOrder
                )
                getTodoItems()
            }
            is TodoListEvent.ToggleCompleted -> {
                viewModelScope.launch(dispatcher + errorHandler) {
                    todoUsecases.toggleCompleteTodoitem(todo = event.todo)
                    getTodoItems()
                }
            }
            is TodoListEvent.ToogleArchived -> {
                viewModelScope.launch(dispatcher + errorHandler) {
                    todoUsecases.toggleArchiveTodoItemUsecase(todo = event.todo)
                    getTodoItems()
                }
            }
            TodoListEvent.undoDelete -> {
                viewModelScope.launch(dispatcher + errorHandler) {
                    todoUsecases.addTodoItemUsecase(todo = undoTodoItem ?: return@launch)
                    undoTodoItem=null
                    getTodoItems()
                }
            }
        }
    }


    fun getTodoItems(){
        getTodoItemsJob?.cancel()

        getTodoItemsJob = viewModelScope.launch(dispatcher + errorHandler) {
            val result = todoUsecases.getAllTodos(
                todoItemsOrder = _state.value.todoItemsOrder
            )
            when(result){
               is TodoUseCaseResult.Sucess ->{
                    _state.value =_state.value.copy(
                        loading = false,
                        todoItems = result.todoItems,
                        todoItemsOrder = _state.value.todoItemsOrder,
                    )
               }
                is TodoUseCaseResult.Error ->{
                    _state.value =_state.value.copy(
                        loading = false,
                        error = Todo_List_String_Resource.CANT_GET_TODOS
                    )
                }
            }
        }

    }

}