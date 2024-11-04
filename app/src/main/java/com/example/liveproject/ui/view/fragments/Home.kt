package com.example.liveproject.ui.view.fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.liveproject.R
import com.example.liveproject.data.local.PreferencesManager
import com.example.liveproject.databinding.FragmentHomeBinding
import com.example.liveproject.ui.view.activities.SignUpActivity
import com.example.liveproject.ui.view.adapters.CategoriesAdapter
import com.example.liveproject.ui.view.adapters.CategoryItem
import com.example.liveproject.ui.view.adapters.NewArrivalItemAdapter
import com.example.liveproject.ui.view.adapters.ViewPagerAdapter
import com.example.liveproject.ui.view.adapters.newArrivalItem

class Home : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var preferencesManager: PreferencesManager

    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: ViewPagerAdapter

    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0

    private val runnable = object : Runnable {
        override fun run() {
            // Change the page
            if (currentPage == pagerAdapter.itemCount) {
                currentPage = 0
            }
            viewPager.setCurrentItem(currentPage++, true)
            // Schedule the next execution
            handler.postDelayed(this, 3000) // Change images every 3 seconds
        }
    }

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
        setupSliderDots()

        // Start automatic image slider
        handler.postDelayed(runnable, 3000) // Start the automatic slider with a delay



        // Create a list of items for the CategoryRecyclerView
        val categoryItems = listOf(
            CategoryItem(R.drawable.category1, "Cartridges &amp; Vape Pens"),
            CategoryItem(R.drawable.category_2, "Concentrates"),
            CategoryItem(R.drawable.category_3, "Edibles"),
            CategoryItem(R.drawable.category_4, "Flower"),
            CategoryItem(R.drawable.category1, "Cartridges &amp; Vape Pens"),
            CategoryItem(R.drawable.category_2, "Concentrates"),
        )

        // Set up CategoryRecyclerView
        val categoriesAdapter = CategoriesAdapter(categoryItems, findNavController())

        binding.categoriesRv.apply {
            adapter = categoriesAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        }


        // Create a list of items for the RecyclerView2
        val newArrivalItems = listOf(
            newArrivalItem(R.drawable.category_4, "Stiiizy 40’s multi pack", "$35.00"),
            newArrivalItem(R.drawable.category_3, "Stiiizy 40’s multi pack", "$35.00"),
            newArrivalItem(R.drawable.category_2, "Stiiizy 40’s multi pack", "$35.00"),
            newArrivalItem(R.drawable.category1, "Stiiizy 40’s multi pack", "$35.00"),
            newArrivalItem(R.drawable.category_4, "Stiiizy 40’s multi pack", "$35.00"),
            newArrivalItem(R.drawable.category_3, "Stiiizy 40’s multi pack", "$35.00"),
        )

        // Set up RecyclerView2
        val newArrivalItemAdapter = NewArrivalItemAdapter(newArrivalItems, findNavController())

        binding.newProductsRv.apply {
            adapter = newArrivalItemAdapter
            layoutManager =
                GridLayoutManager(requireContext(), 3)
        }


    }

    private fun setupSliderDots() {
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

    override fun onResume() {
        super.onResume()
        // Start the auto slider when the fragment is resumed
        handler.postDelayed(runnable, 3000)
    }

    override fun onPause() {
        super.onPause()
        // Stop the auto slider when the fragment is paused
        handler.removeCallbacks(runnable)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Stop the auto slider when the fragment view is destroyed
        handler.removeCallbacks(runnable)
    }
}
