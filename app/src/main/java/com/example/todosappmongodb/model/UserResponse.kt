package com.example.todosappmongodb.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserResponse(
    @Json(name = "msg")
    val msg: String?,
    @Json(name = "success")
    val success: Boolean,
    @Json(name = "token")
    val token: String,
    @Json(name = "user")
    val user: User?
)