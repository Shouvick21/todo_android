package com.demo1.todo.feature_todo.domain.usecase

import com.demo1.todo.R
import com.demo1.todo.core.utils.stringResource
import com.demo1.todo.feature_todo.domain.model.TodoModel
import com.demo1.todo.feature_todo.domain.repository.Todo_list_repo
import com.demo1.todo.feature_todo.domain.utils.InvalidTodoModelExcepton
import com.demo1.todo.feature_todo.domain.utils.SortingDirection
import com.demo1.todo.feature_todo.domain.utils.TodoItemsOrder
import javax.inject.Inject


class TodoUsecases @Inject constructor(
    // this is the interface repo
    private val repo : Todo_list_repo
) {
    suspend fun addTodoItemUsecase(todo : TodoModel){
        if (todo.title.isBlank() || todo.desciption.isBlank() ){
            throw InvalidTodoModelExcepton(stringResource.todo_title_description_blank_error)
        }
        repo.addTodoItem(todo)
    }

    suspend fun updateTodoItemUsecase(todo: TodoModel){
        if (todo.title.isBlank() || todo.desciption.isBlank() ){
            throw InvalidTodoModelExcepton(stringResource.todo_title_description_blank_error)
        }
        repo.updateTodoItem(todo)

    }
    suspend fun deleteTodoItemUsecase(todo: TodoModel){
        repo.deleteTodoItem(todo)
    }
    suspend fun toggleCompleteTodoitem(todo: TodoModel){
        repo.updateTodoItem(todo.copy(isCompleted = !todo.isCompleted))
    }

    suspend fun toggleArchiveTodoItemUsecase(todo: TodoModel){
        repo.updateTodoItem(todo.copy(archived = !todo.archived))
    }

    suspend fun getSingleTodoByIdUsecase(id : Int) : TodoModel?{
        return repo.getSingleTodoItemById(id)
    }

    suspend fun getAllTodos(
        todoItemsOrder : TodoItemsOrder = TodoItemsOrder.Time(SortingDirection.down, true)
    ) :TodoUseCaseResult{
        var todos = repo.getAllTodosFromLocal()
        if(todos.isEmpty()){
            todos =repo.getAllTodos()
        }
        val filterTodos = if(todoItemsOrder.showArchived){
            todos
        }else{
            todos.filter {
                !it.archived
            }
        }
        return when(todoItemsOrder.sortingDirection){
            is SortingDirection.down ->{
                when(todoItemsOrder){
                    is TodoItemsOrder.Title -> TodoUseCaseResult.Sucess(filterTodos.sortedBy {
                        it.title.lowercase()
                    })
                    is TodoItemsOrder.Time -> TodoUseCaseResult.Sucess(filterTodos.sortedBy {
                        it.timeStamp
                    })
                    is TodoItemsOrder.Completed -> TodoUseCaseResult.Sucess(filterTodos.sortedBy {
                        it.isCompleted
                    })
                }
            }
            is SortingDirection.up ->{
                when(todoItemsOrder){
                    is TodoItemsOrder.Title -> TodoUseCaseResult.Sucess(filterTodos.sortedByDescending {
                        it.title.lowercase()
                    })
                    is TodoItemsOrder.Time -> TodoUseCaseResult.Sucess(filterTodos.sortedByDescending {
                        it.timeStamp
                    })
                    is TodoItemsOrder.Completed -> TodoUseCaseResult.Sucess(filterTodos.sortedByDescending {
                        it.isCompleted
                    })
                }
            }
        }
    }
}

sealed class TodoUseCaseResult{
    data class Sucess(val todoItems : List<TodoModel>) : TodoUseCaseResult()
    data class Error(val todoItems : String) : TodoUseCaseResult()
}