package com.example.liveproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.liveproject.data.model.LoginRequest
import com.example.liveproject.data.model.LoginResponse
import com.example.liveproject.data.repository.AuthRepository

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResponse?>()
    val loginResult: LiveData<LoginResponse?> get() = _loginResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun login(email: String, password: String) {
        val request = LoginRequest(email, password)
        Log.d(TAG, "Attempting to login with email: $email")

        authRepository.loginUser(request, { response ->
            if (response != null) {
                _loginResult.value = response
                Log.d(TAG, "Login successful: ${response.message}")
            } else {
                val errorMessage = "Login failed: Response is null"
                Log.d(TAG, errorMessage)
                _error.value = errorMessage
            }
        }, { errorMessage ->
            // Capture the error message returned from the repository
            Log.d(TAG, "Error occurred during login: $errorMessage")
            _error.value = errorMessage // Update the LiveData with the error message
        })
    }
}
