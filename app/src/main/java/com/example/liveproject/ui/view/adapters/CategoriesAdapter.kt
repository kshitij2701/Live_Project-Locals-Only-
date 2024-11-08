package com.example.liveproject.ui.view.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.liveproject.R

class CategoriesAdapter(
    private val itemList: List<CategoryItem>,
    private val findNavController: NavController
) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    inner class CategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.category_rv_image)
        val itemDesc: TextView = itemView.findViewById(R.id.category_rv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.categories_rvlayout, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val item = itemList[position]
        holder.itemImage.setImageResource(item.imageRes)
        holder.itemDesc.text = item.description

        holder.itemView.setOnClickListener {
//            Snackbar.make(holder.itemView, "Item clicked", Snackbar.LENGTH_SHORT).show()
            Toast.makeText(
                holder.itemView.context,
                "Category RecyclerView Item Clicked",
                Toast.LENGTH_SHORT
            )
                .show()

            // Create a bundle to pass the categoryTitle
            val bundle = Bundle().apply {
                putString("categoryTitle", item.description)
            }

            // Navigate to CategoriesFragment with the bundle
            findNavController.navigate(R.id.action_home2_to_categories2, bundle)
        }

    }

    override fun getItemCount(): Int = itemList.size
}

data class CategoryItem(
    val imageRes: Int,
    val description: String,
)
