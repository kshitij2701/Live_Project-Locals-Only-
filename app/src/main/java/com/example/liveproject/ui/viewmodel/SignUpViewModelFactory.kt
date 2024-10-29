package com.example.liveproject.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.liveproject.data.repository.AuthRepository

class SignUpViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SignUpViewModel(AuthRepository()) as T // Instantiate AuthRepository directly
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
