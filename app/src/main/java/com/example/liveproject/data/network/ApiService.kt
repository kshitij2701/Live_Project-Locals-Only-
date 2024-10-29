package com.example.liveproject.data.network

import com.example.liveproject.data.model.LoginRequest
import com.example.liveproject.data.model.LoginResponse
import com.example.liveproject.data.model.SignUpRequest
import com.example.liveproject.data.model.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("liveProject/auth/register")  // API endpoint for registration
    fun signUpUser(@Body request: SignUpRequest): Call<SignUpResponse>

    @POST("liveProject/auth/login")  // API endpoint for login
    fun loginUser(@Body request: LoginRequest): Call<LoginResponse>

}