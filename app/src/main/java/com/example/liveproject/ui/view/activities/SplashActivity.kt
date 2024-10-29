package com.example.liveproject.ui.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.liveproject.R
import com.example.liveproject.data.local.PreferencesManager
import com.example.liveproject.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize PreferencesManager
        preferencesManager = PreferencesManager(this)

        // Load and start the animation
        val popupAnimation = AnimationUtils.loadAnimation(this, R.anim.popup_animation)
        binding.logoImageView.startAnimation(popupAnimation)

        // Delayed transition based on user email existence
        Handler(Looper.getMainLooper()).postDelayed({
            if (preferencesManager.getUserEmail() != null) {
                // If email exists, redirect to HomeActivity
                Toast.makeText(this, "User already exists", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                // If no email, redirect to SignUpActivity
                startActivity(Intent(this, SignUpActivity::class.java))
            }
            finish() // Close SplashActivity
        }, 2000)
    }
}
