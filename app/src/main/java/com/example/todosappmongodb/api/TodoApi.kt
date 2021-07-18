package com.example.todosappmongodb.api

import com.example.todosappmongodb.model.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface TodoApi
{

   // @POST("api/todo/auth/login")
   // suspend fun login(@Body logInBody: LogInBody) : Response<UserResponse>


    @POST("api/todo/auth/register")
    suspend fun register(@Body auth:Map<String,String>) :  Response<UserResponse>


} // interface closed