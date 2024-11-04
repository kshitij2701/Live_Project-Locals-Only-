package com.example.liveproject.ui.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.liveproject.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {

    // Define your list of images
    private val itemList = listOf(
        R.drawable.slider_2, // Replace with your actual drawable resource
        R.drawable.slider_3, // Replace with your actual drawable resource
        R.drawable.slider_2,  // Replace with your actual drawable resource
        R.drawable.slider_3  // Replace with your actual drawable resource
    )



    // Create the ViewHolder class
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val pagerImage: ImageView = itemView.findViewById(R.id.slider_img)

        fun bind(imageRes: Int) {
            pagerImage.setImageResource(imageRes) // Set the image resource to the ImageView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
