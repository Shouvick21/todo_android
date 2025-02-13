package com.demo1.todo.feature_todo.domain.model

import com.google.gson.annotations.SerializedName

data class TodoModel(
    val id: Int?,
    val title: String,
    val desciption: String,
    val timeStamp: Long,
    val isCompleted: Boolean,
    val archived: Boolean,
)
