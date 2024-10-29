package com.example.liveproject.data.model

data class SignUpResponse(
    val message: String,
    val user: User
)

data class User(
    val id: String,
    val email: String
)
