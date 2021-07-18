package com.example.todosappmongodb.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "avatar")
    val avatar: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "_id")
    val id: String,
    @Json(name = "password")
    val password: String,
    @Json(name = "username")
    val username: String,
    @Json(name = "__v")
    val v: Int
)