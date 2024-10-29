package com.example.liveproject.data.repository

import com.example.liveproject.data.model.LoginRequest
import com.example.liveproject.data.model.LoginResponse
import com.example.liveproject.data.model.SignUpRequest
import com.example.liveproject.data.model.SignUpResponse
import com.example.liveproject.data.network.ApiClient
import com.example.liveproject.data.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {

    private val apiService: ApiService = ApiClient.instance // Use ApiClient to get the instance

    fun signUpUser(
        request: SignUpRequest,
        onSuccess: (SignUpResponse?) -> Unit,
        onError: (String) -> Unit // Change onError to accept a String message
    ) {
        apiService.signUpUser(request).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    // Handle specific error cases based on response code or body
                    val errorMessage = when (response.code()) {
                        400 -> "User already exists." //  custom code -> 400 Conflict
                        else -> "An unknown error occurred." // Fallback error message
                    }
                    onError(errorMessage) // Pass the error message to the callback
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                onError(t.localizedMessage ?: "Network error")
            }
        })
    }

//    fun loginUser(
//        request: LoginRequest,
//        onSuccess: (LoginResponse?) -> Unit,
//        onError: (String) -> Unit // Change onError to accept a String message
//    ) {
//        apiService.loginUser(request).enqueue(object : Callback<LoginResponse> {
//            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
//                if (response.isSuccessful) {
//                    onSuccess(response.body())
//                } else {
//                    // Handle specific error cases
//                    val errorMessage = "Login failed: ${response.message()}"
//                    onError(errorMessage) // Pass the error message to the callback
//                }
//            }
//
//            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
//                onError(t.localizedMessage ?: "Network error")
//            }
//        })
//    }


    fun loginUser(
        request: LoginRequest,
        onSuccess: (LoginResponse?) -> Unit,
        onError: (String) -> Unit // Change onError to accept a String message
    ) {
        apiService.loginUser(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    onSuccess(response.body())
                } else {
                    // Handle specific error cases based on response code or body
                    val errorMessage = when (response.code()) {
                        401 -> "Invalid email or password." // HTTP 401 Unauthorized
                        else -> "An unknown error occurred." // Fallback error message
                    }
                    onError(errorMessage) // Pass the error message to the callback
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                onError(t.localizedMessage ?: "Network error")
            }
        })
    }
}


