package com.example.liveproject.ui.view.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.liveproject.R
import com.example.liveproject.data.network.ApiClient
import com.example.liveproject.data.repository.AuthRepository
import com.example.liveproject.databinding.ActivityLoginBinding
import com.example.liveproject.ui.viewmodel.LoginViewModel
import com.example.liveproject.ui.viewmodel.LoginViewModelFactory
import com.example.liveproject.ui.viewmodel.SignUpViewModel
import com.example.liveproject.ui.viewmodel.SignUpViewModelFactory
import java.util.regex.Pattern
import com.example.liveproject.data.local.PreferencesManager


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var preferencesManager: PreferencesManager

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val authRepository = AuthRepository()
        val factory = LoginViewModelFactory(authRepository)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        // Handle Password field toggle
        binding.PasswordEt.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (isDrawableEndClicked(binding.PasswordEt, event)) {
                    isPasswordVisible = !isPasswordVisible
                    togglePasswordVisibility(binding.PasswordEt, isPasswordVisible)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false // Allow normal EditText behavior
        }

        // Navigate to Forgot Password Page
        binding.toForgotPassPage.setOnClickListener {
            val intent = Intent(this@LoginActivity, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }

        // Handle login button click
        binding.signInButton.setOnClickListener {
            val email = binding.EmailEt.text.toString().trim()
            val password = binding.PasswordEt.text.toString().trim()

            if (validateInput(email, password)) {
                // Proceed with login
                Toast.makeText(this, "Login Validation Successful", Toast.LENGTH_SHORT).show()
                viewModel.login(email, password)
            }
        }

        // Initialize PreferencesManager
        preferencesManager = PreferencesManager(this)

        // Observe login results
        viewModel.loginResult.observe(this) { response ->
            response?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                // Save user email to local storage
                preferencesManager.saveUserEmail(it.user.email ?: "") // Adjust this line based on your LoginResponse structure

                // Navigate to the home screen
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish() // Optional: finish the login activity so user can't return to it
            }
        }

        viewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, "Login failed: $errorMessage", Toast.LENGTH_LONG).show()
        })

    }

    // Helper method to toggle password visibility
    private fun togglePasswordVisibility(editText: EditText, isVisible: Boolean) {
        if (isVisible) {
            // Show the password
            editText.inputType =
                InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_open, 0)
        } else {
            // Hide the password
            editText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            editText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.eye_slash, 0)
        }
        // Move the cursor to the end of the text
        editText.setSelection(editText.text?.length ?: 0)
    }

    private fun isDrawableEndClicked(editText: EditText, event: MotionEvent): Boolean {
        if (editText.compoundDrawables[2] == null) return false

        val drawableEnd = editText.compoundDrawables[2]
        val drawableWidth = drawableEnd.bounds.width()
        val touchAreaStart = editText.right - editText.paddingRight - drawableWidth

        return event.rawX >= touchAreaStart
    }

    // Validate email and password
    private fun validateInput(email: String, password: String): Boolean {
        if (email.isEmpty()) {
            binding.EmailEt.error = "Email is required"
            binding.EmailEt.requestFocus()
            return false
        }

        if (!isValidEmail(email)) {
            binding.EmailEt.error = "Invalid email format"
            binding.EmailEt.requestFocus()
            return false
        }

        if (password.isEmpty()) {
            binding.PasswordEt.error = "Password is required"
            binding.PasswordEt.requestFocus()
            return false
        }

        if (!isValidPassword(password)) {
            binding.PasswordEt.error =
                "Password must be at least 6 characters and include an uppercase letter, a lowercase letter, a number, and a special character"
            binding.PasswordEt.requestFocus()
            return false
        }

        return true
    }

    private fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return emailPattern.matcher(email).matches()
    }

    private fun isValidPassword(password: String): Boolean {
        val passwordPattern = Pattern.compile(
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&#])[A-Za-z\\d@\$!%*?&#]{6,}$"
        )
        return passwordPattern.matcher(password).matches()
    }

    // Placeholder method for login logic
    private fun loginUser(email: String, password: String) {
        Toast.makeText(this, "Login Logic Pending", Toast.LENGTH_SHORT).show()
        // Logic Pending for Actual Login (e.g., Firebase Authentication)
    }
}
