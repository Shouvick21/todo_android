package com.demo1.todo.feature_todo.data.remote.dto

data class RemoteTodoModel(

    val id: Int,
    val title: String,
    val desciption: String,
    val timeStamp: Long,
    val isCompleted: Boolean,
    val archived: Boolean,
    )
