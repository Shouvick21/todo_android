package com.demo1.todo.feature_todo.presentation.todo_new_update

import com.demo1.todo.feature_todo.domain.model.TodoModel
import java.lang.Error

data class Todo_new_update_state(
    val isTitleHintVisible : Boolean = true,
    val isDescriptionHintVisible : Boolean = true,
    val todo : TodoModel = TodoModel(
        title = "",
        desciption = "",
        timeStamp = 0,
        isCompleted = false,
        archived = false,
        id = null
    ),
    val isLoading : Boolean = true,
    val error: String? = null
)
