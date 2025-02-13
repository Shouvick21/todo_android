package com.demo1.todo.feature_todo.domain.repository

import com.demo1.todo.feature_todo.domain.model.TodoModel

interface Todo_list_repo {
    suspend fun getAllTodos() : List<TodoModel>
    suspend fun getAllTodosFromLocal() : List<TodoModel>
    suspend fun getAllTodosFromRemote()
    suspend fun getSingleTodoItemById(id : Int) : TodoModel?
    suspend fun addTodoItem(todo : TodoModel)
    suspend fun updateTodoItem(todo : TodoModel)
    suspend fun deleteTodoItem(todo: TodoModel)
}