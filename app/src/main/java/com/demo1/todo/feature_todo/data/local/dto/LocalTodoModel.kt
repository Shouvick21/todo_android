package com.demo1.todo.feature_todo.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo")
data class LocalTodoModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int?,
    val title : String,
    val desciption : String,
    val timeStamp : Long,
    val isCompleted : Boolean,
    val archived : Boolean,

)
