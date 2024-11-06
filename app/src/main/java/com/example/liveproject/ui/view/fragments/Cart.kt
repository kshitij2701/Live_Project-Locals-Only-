package com.example.liveproject.ui.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liveproject.R
import com.example.liveproject.databinding.FragmentCartBinding
import com.example.liveproject.databinding.FragmentFavouriteBinding
import com.example.liveproject.ui.view.adapters.CartAdapterI
import com.example.liveproject.ui.view.adapters.CartRvIItem


class Cart : Fragment() {

    private lateinit var binding: FragmentCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cartRvIItems = listOf(
            CartRvIItem(
                R.drawable.cart_rvi_item_image,
                "Raw garden disposable - 1",
                "14grm",
                "$17,00",
            ),
            CartRvIItem(
                R.drawable.cart_rvi_item_image,
                "Raw garden disposable - 1",
                "14grm",
                "$17,00",
            ),
            CartRvIItem(
                R.drawable.cart_rvi_item_image,
                "Raw garden disposable - 1",
                "14grm",
                "$17,00",
            ),
            )

        // Set up RecyclerView1
        val cartAdapterI =
            CartAdapterI(cartRvIItems, findNavController())

        binding.cartRvI.apply {
            adapter = cartAdapterI
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

}