package com.demo1.todo.feature_todo.presentation.todo_list

import com.demo1.todo.feature_todo.domain.model.TodoModel
import com.demo1.todo.feature_todo.domain.utils.SortingDirection
import com.demo1.todo.feature_todo.domain.utils.TodoItemsOrder

data class Todo_list_state(
    val todoItems : List<TodoModel> = emptyList(),
    val todoItemsOrder: TodoItemsOrder = TodoItemsOrder.Time(SortingDirection.down, true),
    val loading : Boolean = true,
    val error : String? =null
)
