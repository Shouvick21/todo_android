package com.demo1.todo.feature_todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo1.todo.feature_todo.data.local.dto.LocalTodoModel

@Dao
interface TodoDao {
    @Query("SELECT * FROM todo")
    fun getAllTodoItem() : List<LocalTodoModel>

    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getSingleTodoItemBuId(id : Int) : LocalTodoModel?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllTodoItems(todo : List<LocalTodoModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodoItem(todo : LocalTodoModel) : Long

    @Delete
    suspend fun deleteTodoItem(todo : LocalTodoModel)
}