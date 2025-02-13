package com.demo1.todo.feature_todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo1.todo.feature_todo.data.local.dto.LocalTodoModel

@Dao
interface TodoDao {
    // get list of all todoitem
    @Query("SELECT * FROM todo")
    fun getAllTodoItem() : List<LocalTodoModel>

    // get  a single todo_item
    @Query("SELECT * FROM todo WHERE id = :id")
    suspend fun getSingleTodoItemBuId(id : Int) : LocalTodoModel?

    // add all todo_item to the room after fetching data from api/remote
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllTodoItems(todo : List<LocalTodoModel>)

    // add a single todo_item
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTodoItem(todo : LocalTodoModel) : Long

    // delete a todo_item
    @Delete
    suspend fun deleteTodoItem(todo : LocalTodoModel)
}