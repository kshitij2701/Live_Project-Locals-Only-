package com.example.liveproject.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.example.liveproject.R
import com.example.liveproject.data.local.PreferencesManager
import com.example.liveproject.databinding.FragmentHomeBinding
import com.example.liveproject.ui.view.activities.SignUpActivity
import com.example.liveproject.ui.view.adapters.ViewPagerAdapter

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var preferencesManager: PreferencesManager

    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize PreferencesManager
        preferencesManager = PreferencesManager(requireContext())

        // Retrieve and display the email
        val userEmail = preferencesManager.getUserEmail()
        binding.welcometv.text = "Welcome, $userEmail"

        // Set up logout button listener
        binding.logoutBtn.setOnClickListener {
            // Clear user data
            preferencesManager.clearUserEmail()

            // Show a toast message
            Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()

            // Navigate back to login/signup screen
            val intent = Intent(requireActivity(), SignUpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish() // Close the activity to prevent back navigation
        }


        // Setup ViewPager2 and adapter
        viewPager = binding.imgslider
        pagerAdapter = ViewPagerAdapter()
        viewPager.adapter = pagerAdapter

        // Setup slider dots
        val dotsCount = pagerAdapter.itemCount
        val dots = Array(dotsCount) { ImageView(requireContext()) }

        for (i in 0 until dotsCount) {
            dots[i] = ImageView(requireContext()).apply {
                setImageResource(R.drawable.nonactive_dot) // Replace with your dot drawable
            }
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(8, 0, 8, 0) // Adjust these values to set the gap size
            }
            binding.sliderDots.addView(dots[i], params)
        }

        // Update dots on page change
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotsCount) {
                    dots[i].setImageResource(R.drawable.nonactive_dot) // Replace with your dot drawable
                }
                dots[position].setImageResource(R.drawable.active_dot) // Replace with your dot drawable
            }
        })

    }

}

