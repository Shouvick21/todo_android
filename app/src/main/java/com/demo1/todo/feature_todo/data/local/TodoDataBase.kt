package com.demo1.todo.feature_todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo1.todo.feature_todo.data.local.dto.LocalTodoModel

@Database(
    entities = [LocalTodoModel::class],
    version = 1,
    exportSchema = false
)

abstract class TodoDataBase : RoomDatabase()  {
    abstract val dao : TodoDao

    companion object{
        const val DATABASE_NAME = "todo_db"
    }
}