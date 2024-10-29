package com.example.liveproject.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.liveproject.data.model.SignUpRequest
import com.example.liveproject.data.model.SignUpResponse
import com.example.liveproject.data.network.ApiService
import com.example.liveproject.data.repository.AuthRepository

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _signUpResult = MutableLiveData<SignUpResponse?>()
    val signUpResult: LiveData<SignUpResponse?> get() = _signUpResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    companion object {
        private const val TAG = "SignUpViewModel"
    }

    fun signUp(email: String, password: String) {
        val request = SignUpRequest(email, password)
        Log.d(TAG, "Attempting to sign up with email: $email")

        authRepository.signUpUser(request, { response ->
            if (response != null) {
                _signUpResult.value = response
                Log.d(TAG, "Signup successful: $response")
            } else {
                val errorMessage = "Signup failed: Response is null"
                Log.d(TAG, errorMessage)
                _error.value = errorMessage
            }
        }, { errorMessage ->
            // Capture the error message returned from the repository
            Log.d(TAG, "Error occurred during signup: $errorMessage")
            _error.value = errorMessage // Update the LiveData with the error message
        })
    }
}



