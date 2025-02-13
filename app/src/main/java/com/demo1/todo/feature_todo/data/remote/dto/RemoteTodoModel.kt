package com.demo1.todo.feature_todo.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RemoteTodoModel(
    @SerializedName("ID")
    val id: Int?,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Description")
    val desciption: String,
    @SerializedName("TimeStamp")
    val timeStamp: Long,
    @SerializedName("Completed")
    val isCompleted: Boolean,
    @SerializedName("Archived")
    val archived: Boolean,
    )