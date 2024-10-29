package com.example.liveproject.data.model

data class LoginResponse(
    val message: String,
    val token: String,
    val user: User
)
