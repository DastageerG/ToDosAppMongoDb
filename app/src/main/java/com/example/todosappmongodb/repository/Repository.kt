package com.example.todosappmongodb.repository

import com.example.todosappmongodb.api.TodoApi
import com.example.todosappmongodb.model.UserResponse
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject


@ActivityRetainedScoped
class Repository @Inject constructor(private val todoApi: TodoApi)
{

  suspend fun login(auth:Map<String,String>) : Response<UserResponse> = todoApi.login(auth)

  suspend fun register(auth:Map<String,String>) : Response<UserResponse> = todoApi.register(auth)

}