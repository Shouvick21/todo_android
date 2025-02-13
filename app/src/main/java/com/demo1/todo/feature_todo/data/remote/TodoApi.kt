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
    @GET("todo.json")
    suspend fun getAllRemoteTodos() : List<RemoteTodoModel>

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

    @PUT
    suspend fun postRemoteSingleTodo(
        @Url url : String,
        @Body updatedTodo : RemoteTodoModel
    ) : Response<Unit>

    @DELETE("todo/{id}.json")
    suspend fun deleteRemoteTodo(
        @Path("id") id : Int?
    ) : Response<Unit>

    @PUT("todo/{id}.json")
    suspend fun UpdateRemoteTodo(
        @Path("id") id : Int?,
        @Body todoItem : RemoteTodoModel
    ) : Response<Unit>

}