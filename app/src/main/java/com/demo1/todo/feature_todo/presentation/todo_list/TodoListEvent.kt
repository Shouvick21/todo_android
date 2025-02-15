package com.demo1.todo.feature_todo.presentation.todo_list

import com.demo1.todo.feature_todo.domain.model.TodoModel
import com.demo1.todo.feature_todo.domain.utils.TodoItemsOrder

sealed class TodoListEvent {
    data class Sort(val todoItemsOrder: TodoItemsOrder) : TodoListEvent()
    data class Delete(val todo : TodoModel) : TodoListEvent()
    data class ToggleCompleted(val todo : TodoModel) : TodoListEvent()
    data class ToogleArchived(val todo : TodoModel) : TodoListEvent()
    object undoDelete : TodoListEvent()
}