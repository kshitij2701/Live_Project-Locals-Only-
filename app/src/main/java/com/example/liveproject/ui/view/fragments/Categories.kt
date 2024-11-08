package com.example.liveproject.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.liveproject.R
import com.example.liveproject.databinding.FragmentCategoriesBinding
import com.example.liveproject.databinding.FragmentSearchResultBinding
import com.example.liveproject.ui.view.adapters.ProductItem
import com.example.liveproject.ui.view.adapters.SearchAdapter


class Categories : Fragment(R.layout.fragment_categories) {

    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var searchAdapter: SearchAdapter
    private val itemList = listOf(
        ProductItem(R.drawable.item11, "Lorem ipsum dolor sit amet consectetur", "$17,00"),
        ProductItem(R.drawable.category_4, "Item 2", "$20,00"),
        ProductItem(R.drawable.category1, "Concentrate", "$20,00"),
        ProductItem(R.drawable.category_2, "Item 4", "$20,00"),
        ProductItem(R.drawable.category_3, "Edibles", "$20,00"),
        ProductItem(R.drawable.category_4, "Item 6", "$20,00"),
        ProductItem(R.drawable.item11, "Flower", "$20,00"),
        ProductItem(R.drawable.category1, "Item 8", "$20,00")
        // Add more items as needed
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupParticularCategoryRecyclerView()

        // Get the categoryTitle argument
        val categoryTitle = arguments?.getString("categoryTitle")

        // Set the categoryTitle to the TextView
        binding.categoryTitleTv.text = categoryTitle ?: "All Category"

    }

    private fun setupParticularCategoryRecyclerView() {
        searchAdapter = SearchAdapter(itemList, binding.root)
        binding.particularCategoryRv.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }
}


