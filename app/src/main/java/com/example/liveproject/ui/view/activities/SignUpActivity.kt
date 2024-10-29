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
import com.example.liveproject.data.network.ApiService
import com.example.liveproject.data.repository.AuthRepository
import com.example.liveproject.databinding.ActivitySignUpBinding
import com.example.liveproject.ui.viewmodel.SignUpViewModel
import com.example.liveproject.ui.viewmodel.SignUpViewModelFactory
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var viewModel: SignUpViewModel
    private var isPasswordVisible = false
    private var isConfirmPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Create the ViewModel using the factory
        viewModel = ViewModelProvider(this, SignUpViewModelFactory()).get(SignUpViewModel::class.java)

        // Now you can use your ViewModel
        // For example, set up button click listeners to trigger the sign-up process


        // Handle Password field toggle
        binding.PasswordEt.setOnTouchListener { _, event ->  // Use _ instead of v
            if (event.action == MotionEvent.ACTION_UP) {
                if (isDrawableEndClicked(binding.PasswordEt, event)) {
                    isPasswordVisible = !isPasswordVisible
                    togglePasswordVisibility(binding.PasswordEt, isPasswordVisible)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false // Allow normal EditText behavior
        }


        // Handle Confirm Password field toggle
        binding.ConfirmPasswordEt.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (isDrawableEndClicked(binding.ConfirmPasswordEt, event)) {
                    isConfirmPasswordVisible = !isConfirmPasswordVisible
                    togglePasswordVisibility(binding.ConfirmPasswordEt, isConfirmPasswordVisible)
                    return@setOnTouchListener true
                }
            }
            return@setOnTouchListener false // Allow normal EditText behavior
        }

        // Navigate to Login Page
        binding.alreadyHaveAccountLayout.setOnClickListener {
            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.signUpButton.setOnClickListener {
            val email = binding.EmailEt.text.toString().trim()
            val password = binding.PasswordEt.text.toString().trim()
            val confirmPassword = binding.ConfirmPasswordEt.text.toString().trim()

            if (validateInput(email, password, confirmPassword)) {
                // Proceed with login or signup
                Toast.makeText(this, "SignUp Validation Successful", Toast.LENGTH_SHORT).show()
                viewModel.signUp(email, password)
            }
        }

        viewModel.signUpResult.observe(this, Observer { response ->
            response?.let {
                Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                // Navigate to login or home screen, if necessary
                val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                startActivity(intent)
            }
        })

        viewModel.error.observe(this, Observer { errorMessage ->
            Toast.makeText(this, "Signup failed: $errorMessage", Toast.LENGTH_LONG).show()
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
        editText.setSelection(editText.text.length)
    }

    private fun isDrawableEndClicked(editText: EditText, event: MotionEvent): Boolean {
        if (editText.compoundDrawables[2] == null) return false

        // Padding adjustments if necessary
        val drawableEnd = editText.compoundDrawables[2]
        val drawableWidth = drawableEnd.bounds.width()

        // Get the right boundary, considering padding and drawable width
        val touchAreaStart = editText.right - editText.paddingRight - drawableWidth

        // Check if the touch event was within the drawable's bounds
        return event.rawX >= touchAreaStart
    }


//    private fun signUpUser(email: String, password: String) {
//
//        Toast.makeText(this, "SignUp Logic Pending", Toast.LENGTH_SHORT).show()
//        // Logic Pending of Actual SignUp
//
////        firebaseAuth.createUserWithEmailAndPassword(email, password)
////            .addOnCompleteListener(this) {
////                if (it.isSuccessful) {
////                    Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_SHORT).show()
////
////                    // Get the currently signed-in user
////                    val user = firebaseAuth.currentUser
////
////                    // Create a new user document in Firestore
////                    val userData = hashMapOf(
////                        "fullName" to fullName,
////                        "email" to email
////                    )
////
////                    user?.let {
////                        firestore.collection("users").document(it.uid)
////                            .set(userData)
////                            .addOnSuccessListener {
////                                // Redirect to login activity
////                                val intent = Intent(this@signupActivity, loginActivity::class.java)
////                                startActivity(intent)
////                                finish()
////                            }
////                            .addOnFailureListener { e ->
////                                Toast.makeText(
////                                    this,
////                                    "Error Storing User Data: ${e.message}",
////                                    Toast.LENGTH_SHORT
////                                ).show()
////                            }
////                    }
////                } else {
////                    Toast.makeText(this, "Error Creating User", Toast.LENGTH_SHORT).show()
////                }
////            }
//
//    }


    private fun validateInput(
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {

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

        if (password != confirmPassword) {
            binding.ConfirmPasswordEt.error = "Passwords do not match"
            binding.ConfirmPasswordEt.requestFocus()
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


}
