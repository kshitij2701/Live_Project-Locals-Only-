package com.example.liveproject.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.example.liveproject.R

class NewArrivalItemAdapter(
    private val itemList: List<newArrivalItem>,
    private val findNavController: NavController
) :
    RecyclerView.Adapter<NewArrivalItemAdapter.NewArrivalViewHolder>() {

    inner class NewArrivalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val na_itemImage: ImageView = itemView.findViewById(R.id.newProducts_rv_image)
        val na_itemDesc: TextView = itemView.findViewById(R.id.newProducts_rv_desc)
        val na_itemPrice: TextView = itemView.findViewById(R.id.newProducts_rv_price)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewArrivalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.new_products_rvlayout, parent, false)
        return NewArrivalViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: NewArrivalViewHolder, position: Int) {
        val item = itemList[position]
        holder.na_itemImage.setImageResource(item.imageRes)
        holder.na_itemDesc.text = item.description
        holder.na_itemPrice.text = item.price

        holder.itemView.setOnClickListener {
//          Snackbar.make(holder.itemView, "Item clicked", Snackbar.LENGTH_SHORT).show()
            Toast.makeText(
                holder.itemView.context,
                "RecyclerView 2 Item Clicked",
                Toast.LENGTH_SHORT
            )
                .show()
//            findNavController.navigate(R.id.action_home3_to_products2)

        }
    }


}

data class newArrivalItem(
    val imageRes: Int,
    val description: String,
    val price: String
)