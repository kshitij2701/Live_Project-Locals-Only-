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

class WishlistItemsAdapter(private val itemList: List<WishlistItem>, private val parentView: View) :
    RecyclerView.Adapter<WishlistItemsAdapter.WishlistItemsViewHolder>() {

    inner class WishlistItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wishlistItemImage: ImageView = itemView.findViewById(R.id.wishlist_item_image)
        val wishlistItemHeading: TextView =
            itemView.findViewById(R.id.wishlist_item_heading)
        val wishlistItemDesc: TextView =
            itemView.findViewById(R.id.wishlist_item_desc)
        val wishlistItemPrice: TextView =
            itemView.findViewById(R.id.priceRange_tv)
        val wishlistItemAddIcon: ImageView =
            itemView.findViewById(R.id.add_button_wishlist)
        val wishlistItemRemoveIcon: ImageView =
            itemView.findViewById(R.id.wishlist_remove_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishlistItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.wishlist_item_layout, parent, false)
        return WishlistItemsViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size


    override fun onBindViewHolder(holder: WishlistItemsViewHolder, position: Int) {
        val item = itemList[position]
        holder.wishlistItemImage.setImageResource(item.imageRes)
        holder.wishlistItemHeading.text = item.wishlistitemHeading
        holder.wishlistItemDesc.text = item.wishlistitemDescription
        holder.wishlistItemPrice.text = item.wishlistitemPrice


        holder.itemView.setOnClickListener {
//            Toast.makeText(
//                holder.itemView.context,
//                "ProductItem RecyclerView Clicked",
//                Toast.LENGTH_SHORT
//            )
//                .show()
        }

        holder.wishlistItemAddIcon.setOnClickListener {

        }

        holder.wishlistItemRemoveIcon.setOnClickListener {

        }

    }

}

data class WishlistItem(
    val imageRes: Int,
    val wishlistitemHeading: String,
    val wishlistitemDescription: String,
    val wishlistitemPrice: String,
)