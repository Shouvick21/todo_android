package com.demo1.todo.feature_todo.data.remote

import com.demo1.todo.feature_todo.data.remote.dto.RemoteTodoModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface TodoApi {
    // get list of todo_item from api
    @GET("todo.json")
    suspend fun getAllRemoteTodos() : List<RemoteTodoModel>

    // get single todo_Item from api
    // i write -> \"ID\"" because retrofit don't take query in uppercase to solve this issue
    // @Query is to add a query to url Final link becoming -> "todo.json?orderBy=ID&equalTo={id}"
    @GET("todo.json?orderBy=\"ID\"")
    suspend fun getSingleRemoteTodo(
        @Query("equalTo") id : Int?
    ) : Map< String , RemoteTodoModel>
//
//    @POST("todo.json")
//    suspend fun postRemoteSingleTodo(
//        @Body url : String,
//        @Body updatedTodo : RemoteTodoModel
//    ) : Response<Unit>

    // i used put to add new data in firebase. because if i do post retrofit assign a very long String id for that todo_item which is bad
    // @Url to pass end point means what we write("") here
    @PUT
    suspend fun postRemoteSingleTodo(
        @Url url : String,
        @Body updatedTodo : RemoteTodoModel
    ) : Response<Unit>

    // it will delete a todo_item from firebase
    @DELETE("todo/{id}.json")
    suspend fun deleteRemoteTodo(
        @Path("id") id : Int?
    ) : Response<Unit>

    // it is to update a todo_item
    @PUT("todo/{id}.json")
    suspend fun UpdateRemoteTodo(
        @Path("id") id : Int?,
        @Body todoItem : RemoteTodoModel
    ) : Response<Unit>
}