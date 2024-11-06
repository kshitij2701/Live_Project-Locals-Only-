package com.example.liveproject.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liveproject.R
import com.example.liveproject.databinding.FragmentFavouriteBinding
import com.example.liveproject.databinding.FragmentHomeBinding
import com.example.liveproject.ui.view.adapters.MostPopularProductsAdapter
import com.example.liveproject.ui.view.adapters.NewArrivalItemAdapter
import com.example.liveproject.ui.view.adapters.WishlistItem
import com.example.liveproject.ui.view.adapters.WishlistItemsAdapter
import com.example.liveproject.ui.view.adapters.mostPopularProductsItem
import com.example.liveproject.ui.view.adapters.newArrivalItem


class Favourite : Fragment() {

    private lateinit var binding: FragmentFavouriteBinding
    private var isWishlistEmpty: Boolean = true // Your custom variable to control visibility

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Create a list of items for the Most PopularRecyclerView
        val mostPopularProductsItems = listOf(
            mostPopularProductsItem(R.drawable.mostpopular_item_img, "Stiiizy 40’s multi pack"),
            mostPopularProductsItem(R.drawable.category_3, "Stiiizy 40’s multi pack"),
            mostPopularProductsItem(R.drawable.mostpopular_item_img, "Stiiizy 40’s multi pack"),
        )

        // Set up RecyclerView2
        val mostPopularProductsAdapter =
            MostPopularProductsAdapter(mostPopularProductsItems, findNavController())

        binding.MostPopularProductsRv.apply {
            adapter = mostPopularProductsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }


        // Set the custom variable as per your conditions
        // Example: Setting `isWishlistEmpty` to false if items are present
        // You can change this condition based on your logic

        isWishlistEmpty = false // Change this value as needed

        updateLayoutVisibility(isWishlistEmpty)

        if (!isWishlistEmpty) {
            // Create a list of items for the Wishlist RecyclerView
            val wishlistItems = listOf(
                WishlistItem(
                    R.drawable.wishlist_itemimg_i,
                    "Concentrates",
                    "Upper Echolon Tincture ",
                    "$80.00–$275.00",
                ),
                WishlistItem(
                    R.drawable.wishlist_itemimg_ii,
                    "Concentrates",
                    "Upper Echolon Tincture ",
                    "$80.00–$275.00",
                ),
                WishlistItem(
                    R.drawable.wishlist_itemimg_iii,
                    "Concentrates",
                    "Upper Echolon Tincture ",
                    "$80.00–$275.00",
                ),
                WishlistItem(
                    R.drawable.wishlist_itemimg_iv,
                    "Concentrates",
                    "Upper Echolon Tincture ",
                    "$80.00–$275.00",
                ),
                WishlistItem(
                    R.drawable.wishlist_itemimg_i,
                    "Concentrates",
                    "Upper Echolon Tincture ",
                    "$80.00–$275.00",
                ),
                WishlistItem(
                    R.drawable.wishlist_itemimg_ii,
                    "Concentrates",
                    "Upper Echolon Tincture ",
                    "$80.00–$275.00",
                ),
                WishlistItem(
                    R.drawable.wishlist_itemimg_iii,
                    "Concentrates",
                    "Upper Echolon Tincture ",
                    "$80.00–$275.00",
                ),
                WishlistItem(
                    R.drawable.wishlist_itemimg_iv,
                    "Concentrates",
                    "Upper Echolon Tincture ",
                    "$80.00–$275.00",
                ),
            )

            // Set up RecyclerView if there are items
            val wishlistItemsAdapter = WishlistItemsAdapter(wishlistItems, binding.root)
            binding.wishlistRecyclerView.apply {
                adapter = wishlistItemsAdapter
                layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            }

        }

    }

    private fun updateLayoutVisibility(isEmpty: Boolean) {
        if (isEmpty) {
            binding.emptyWishlistLayout.visibility = View.VISIBLE
            binding.wishlistRecyclerView.visibility = View.GONE
        } else {
            binding.emptyWishlistLayout.visibility = View.GONE
            binding.wishlistRecyclerView.visibility = View.VISIBLE
        }
    }


}