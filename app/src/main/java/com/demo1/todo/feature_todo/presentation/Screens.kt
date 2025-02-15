package com.demo1.todo.feature_todo.presentation

sealed class Screens(val route : String) {
    object TodoListScreen : Screens("todoList_Screen")
    object TodoUpdateScreen : Screens("todoUpdate_Screen")
}