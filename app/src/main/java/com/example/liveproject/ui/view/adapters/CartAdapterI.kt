package com.example.liveproject.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.liveproject.R

class CartAdapterI(
    private val itemList: List<CartRvIItem>,
    private val findNavController: NavController
) :
    RecyclerView.Adapter<CartAdapterI.CartAdapterIViewHolder>() {

    inner class CartAdapterIViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.cart_rv_i_item_image)
        val itemName: TextView = itemView.findViewById(R.id.cart_rv_i_item_heading)
        val itemContent: TextView = itemView.findViewById(R.id.cart_rv_i_item_content)
        val itemQuantityLessIcon: ImageView = itemView.findViewById(R.id.less_icon)
        val itemQuantityCountIndicator: TextView = itemView.findViewById(R.id.quantity_count_tv)
        val itemQuantityMoreIcon: ImageView = itemView.findViewById(R.id.more_icon)
        val itemPrice: TextView = itemView.findViewById(R.id.price_tv)
        val itemRemoveBtn: ImageView = itemView.findViewById(R.id.cart_rv_i_remove_btn)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapterIViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_rv_i_item_layout, parent, false)
        return CartAdapterIViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapterIViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemImage.setImageResource(item.imageRes)
        holder.itemName.text = item.item_name
        holder.itemContent.text = item.item_content
        holder.itemPrice.text = item.item_price


        holder.itemQuantityLessIcon.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Product Quantity less icon Clicked",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        holder.itemQuantityCountIndicator.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Product Quantity Indicator icon Clicked",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        holder.itemQuantityMoreIcon.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Product Quantity More icon Clicked",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        holder.itemRemoveBtn.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Item Remove Btn Clicked",
                Toast.LENGTH_SHORT
            )
                .show()
        }

        holder.itemView.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Items Summary RecyclerView Item Clicked",
                Toast.LENGTH_SHORT
            )
                .show()
        }


    }

    override fun getItemCount(): Int = itemList.size

}

data class CartRvIItem(
    val imageRes: Int,
    val item_name: String,
    val item_content: String,
    val item_price: String,
)
