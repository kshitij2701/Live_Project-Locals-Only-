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

class MostPopularProductsAdapter(
    private val itemList: List<mostPopularProductsItem>,
    private val findNavController: NavController
) :
    RecyclerView.Adapter<MostPopularProductsAdapter.MostPopularProductsViewHolder>() {

    inner class MostPopularProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val na_itemImage: ImageView = itemView.findViewById(R.id.mostPopular_rv_image)
        val na_itemDesc: TextView = itemView.findViewById(R.id.mostPopular_rv_desc)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MostPopularProductsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.most_popularproducts_rvlayout, parent, false)
        return MostPopularProductsViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: MostPopularProductsViewHolder, position: Int) {
        val item = itemList[position]
        holder.na_itemImage.setImageResource(item.imageRes)
        holder.na_itemDesc.text = item.description

        holder.itemView.setOnClickListener {
//          Snackbar.make(holder.itemView, "Item clicked", Snackbar.LENGTH_SHORT).show()
            Toast.makeText(
                holder.itemView.context,
                "RecyclerView  Item Clicked",
                Toast.LENGTH_SHORT
            )
                .show()
//            findNavController.navigate(R.id.action_home3_to_products2)

        }
    }


}

data class mostPopularProductsItem(
    val imageRes: Int,
    val description: String
)