package com.example.liveproject.ui.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.liveproject.R
import com.example.liveproject.databinding.ActivityForgetPasswordBinding
import com.example.liveproject.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class ForgetPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgetPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set onClickListener for the reset password button
        binding.forgotPasswordButton.setOnClickListener {
            val email = binding.EmailEt.text.toString().trim()
            if (isValidEmail(email)) {

                Toast.makeText(this, "Logic to reset password pending", Toast.LENGTH_SHORT).show()

                // logic to reset password pending
                // Proceed with password reset logic (e.g., call Firebase Auth)
                //Toast.makeText(this, "Password reset link sent to $email", Toast.LENGTH_SHORT).show()
            } else {
                binding.EmailEt.error = "Invalid email format"
                binding.EmailEt.requestFocus()
            }
        }

    }

    private fun isValidEmail(email: String): Boolean {
        // Basic email validation regex
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return emailPattern.matcher(email).matches()
    }
}