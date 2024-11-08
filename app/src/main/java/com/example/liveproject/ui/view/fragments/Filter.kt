package com.example.liveproject.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.liveproject.R
import com.example.liveproject.databinding.FragmentFilterBinding
import com.example.liveproject.databinding.FragmentHomeBinding

class Filter : Fragment() {
    private lateinit var binding: FragmentFilterBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RangeSlider listener to update price_range_tv
        binding.priceRangeSlider.addOnChangeListener { slider, _, _ ->
            val values = slider.values
            val minPrice = values[0].toInt()
            val maxPrice = values[1].toInt()
            binding.priceRangeTv.text = "$$minPrice - $$maxPrice"
        }

        // Set up Clear button listener
        binding.clearButton.setOnClickListener {
            // Reset slider values and update price range text
            binding.priceRangeSlider.values = listOf(
                binding.priceRangeSlider.valueFrom,
                binding.priceRangeSlider.valueTo
            )
            binding.priceRangeTv.text =
                "$${binding.priceRangeSlider.valueFrom.toInt()} - $${binding.priceRangeSlider.valueTo.toInt()}"
            // Show toast message
            Toast.makeText(requireContext(), "Filters cleared", Toast.LENGTH_SHORT).show()
        }

        // Set up Apply button listener
        binding.applyButton.setOnClickListener {
            // Show toast message with the current price range
            Toast.makeText(
                requireContext(),
                "Filters applied with price range: ${binding.priceRangeTv.text}",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Set up Close button listener to close the fragment
        binding.closeButton.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

}