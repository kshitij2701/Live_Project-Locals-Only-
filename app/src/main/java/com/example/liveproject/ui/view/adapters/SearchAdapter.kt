package com.example.liveproject.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.liveproject.R

class SearchAdapter(
    private var itemList: List<ProductItem>, // Original list
    private val parentView: View
) : RecyclerView.Adapter<SearchAdapter.SearchItemsViewHolder>() {

    private var filteredItemList = itemList.toMutableList() // List that will be displayed

    inner class SearchItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val searchItemImage: ImageView = itemView.findViewById(R.id.search_item_image)
        val searchItemHeading: TextView = itemView.findViewById(R.id.search_item_heading)
        val searchItemPrice: TextView = itemView.findViewById(R.id.search_item_price_tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.search_rv_item_layout, parent, false)
        return SearchItemsViewHolder(view)
    }

    override fun getItemCount(): Int = filteredItemList.size

    override fun onBindViewHolder(holder: SearchItemsViewHolder, position: Int) {
        val item = filteredItemList[position]
        holder.searchItemImage.setImageResource(item.imageRes)
        holder.searchItemHeading.text = item.itemHeading
        holder.searchItemPrice.text = item.itemPrice
    }

    // Method to update the list based on the search query
    fun updateList(newList: List<ProductItem>) {
        filteredItemList = newList.toMutableList()
        notifyDataSetChanged()
    }
}


data class ProductItem(
    val imageRes: Int,
    val itemHeading: String,
    val itemPrice: String,
)

