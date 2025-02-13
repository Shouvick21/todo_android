package com.demo1.todo.feature_todo.data.repository

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.demo1.todo.feature_todo.data.di.IoDipatcher
import com.demo1.todo.feature_todo.data.local.TodoDao
import com.demo1.todo.feature_todo.data.mapper.toListOfLocalTodoModelFromRemote
import com.demo1.todo.feature_todo.data.mapper.toListOfTodoModelFromLocal
import com.demo1.todo.feature_todo.data.mapper.toLocalTodomodel
import com.demo1.todo.feature_todo.data.mapper.toRemoteTodomodel
import com.demo1.todo.feature_todo.data.mapper.toTodomodel
import com.demo1.todo.feature_todo.data.remote.TodoApi
import com.demo1.todo.feature_todo.domain.model.TodoModel
import com.demo1.todo.feature_todo.domain.repository.Todo_list_repo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.ConnectException
import java.net.HttpRetryException
import java.net.UnknownHostException

class TodoListRepoImplementation (
    private val dao : TodoDao,
    private val api : TodoApi,
   @IoDipatcher private val dispatcher : CoroutineDispatcher
) : Todo_list_repo {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getAllTodos(): List<TodoModel> {
        getAllTodosFromRemote()
        return dao.getAllTodoItem().toListOfTodoModelFromLocal()
    }

    override suspend fun getAllTodosFromLocal(): List<TodoModel> {
       return dao.getAllTodoItem().toListOfTodoModelFromLocal()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getAllTodosFromRemote() {
        return withContext(dispatcher){
            try {
                refreshRoomCache()
            }catch (e : Exception){
                when(e){
                    is UnknownHostException, is ConnectException , is HttpException ->{
                        Log.e("TAG", "Error : No data from Remote ")
                        if(isLocalCacheEmpty()){
                            Log.e("TAG", "Error  :No data from local room Cache " )
                            throw Exception("Error : Device offline and no \n no data from local")
                        }
                    }
                    else ->throw e
                }
            }
        }
    }

    private fun isLocalCacheEmpty() : Boolean{
     var empty = true
     if(dao.getAllTodoItem().isNotEmpty()) empty = false
     return empty
    }

    private suspend fun refreshRoomCache(){
        val remoteBooks = api.getAllRemoteTodos().filterNotNull()
        dao.addAllTodoItems(remoteBooks.toListOfLocalTodoModelFromRemote())
    }

    override suspend fun getSingleTodoItemById(id: Int): TodoModel? {
        return dao.getSingleTodoItemBuId(id)?.toTodomodel()

    }

    override suspend fun addTodoItem(todo: TodoModel) {
        val newId = dao.addTodoItem(todo.toLocalTodomodel())
        val id = newId.toInt()
        val url = "todo/$id.json"
        api.postRemoteSingleTodo(url = url, updatedTodo = todo.toRemoteTodomodel().copy(id = id))
    }

    override suspend fun updateTodoItem(todo: TodoModel) {
        dao.addTodoItem(todo.toLocalTodomodel())
        api.UpdateRemoteTodo(todo.id,todo.toRemoteTodomodel())
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun deleteTodoItem(todo: TodoModel) {
        try {
            val response = api.deleteRemoteTodo(todo.id)
            if(response.isSuccessful){
                Log.i("TAG", "delete from api  ")
            }else{
                Log.i("TAG", "Error occure in line 92 from TodoListRepoImplemantatioon.kt  ")
                Log.i("TAG", "delete from api  ")

            }

        }
        catch (e : Exception){
            when(e){
                is UnknownHostException, is ConnectException , is HttpException -> {
                    Log.e("TAG", "Error: Could not delete ", )
                }
                else -> throw  e
            }
        }
    }
}