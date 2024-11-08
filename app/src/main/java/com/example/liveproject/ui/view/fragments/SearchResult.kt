package com.example.liveproject.ui.view.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liveproject.R
import com.example.liveproject.databinding.FragmentSearchResultBinding
import com.example.liveproject.ui.view.adapters.MostPopularProductsAdapter
import com.example.liveproject.ui.view.adapters.ProductItem
import com.example.liveproject.ui.view.adapters.SearchAdapter
import com.example.liveproject.ui.view.adapters.mostPopularProductsItem

class SearchResult : Fragment() {

    private lateinit var binding: FragmentSearchResultBinding
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
    ): View {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMostPopularProductsRecyclerView()
        setupSearchRecyclerView()
        setupSearchEditText()

        binding.filterIconBtn.setOnClickListener {
            findNavController().navigate(R.id.action_searchResult_to_filter)
        }
    }

    /**
     * Sets up the "Most Popular Products" RecyclerView with a horizontal layout.
     */
    private fun setupMostPopularProductsRecyclerView() {
        val mostPopularProductsItems = listOf(
            mostPopularProductsItem(R.drawable.mostpopular_item_img, "Stiiizy 40’s multi pack"),
            mostPopularProductsItem(R.drawable.category_3, "Stiiizy 40’s multi pack"),
            mostPopularProductsItem(R.drawable.mostpopular_item_img, "Stiiizy 40’s multi pack"),
        )

        val mostPopularProductsAdapter =
            MostPopularProductsAdapter(mostPopularProductsItems, findNavController())
        binding.MostPopularProductsRv.apply {
            adapter = mostPopularProductsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    /**
     * Sets up the search RecyclerView with a grid layout and hides it by default.
     */
    private fun setupSearchRecyclerView() {
        searchAdapter = SearchAdapter(itemList, binding.root)
        binding.searchRv.apply {
            adapter = searchAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            visibility = View.GONE
        }
    }

    /**
     * Adds a TextWatcher to the search EditText to filter the search RecyclerView
     * based on the user's query and manages visibility.
     */
    private fun setupSearchEditText() {
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s.toString().trim()
                if (query.isEmpty()) {
                    // Clear RecyclerView when no query
                    binding.searchRv.visibility = View.GONE
                    binding.belowSearchLayout.visibility = View.VISIBLE
                    searchAdapter.updateList(emptyList())
                } else {
                    // Filter items and update RecyclerView
                    val filteredList =
                        itemList.filter { it.itemHeading.contains(query, ignoreCase = true) }
                    searchAdapter.updateList(filteredList)

                    // Show or hide RecyclerView based on search results
                    binding.belowSearchLayout.visibility = View.GONE
                    binding.searchRv.visibility =
                        if (filteredList.isEmpty()) View.GONE else View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}
