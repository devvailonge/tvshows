package com.devvailonge.tvshows.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.home.R
import com.devvailonge.tvshows.home.CategoryAdapter.CategoryViewHolder
import com.devvailonge.tvshows.home.HomeFragment.*
import com.google.android.material.chip.Chip

class CategoryAdapter (private val categorie : List<Category> )
    : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categorie[position])
    }

    override fun getItemCount(): Int {
        return categorie.size
    }


    class CategoryViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(data: Category) {
            with(itemView) {
                val chipCategory = findViewById<Chip>(R.id.chipCategory)

                chipCategory.text = data.category
            }
        }
    }


}








