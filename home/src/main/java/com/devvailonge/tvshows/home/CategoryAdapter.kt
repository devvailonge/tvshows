package com.devvailonge.tvshows.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devvailonge.home.R
import com.devvailonge.tvshows.home.CategoryAdapter.CategoryViewHolder
import com.devvailonge.tvshows.home.HomeFragment.Category
import com.google.android.material.chip.Chip

class CategoryAdapter(private val callback: (Category) -> Unit) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var categorie: List<Category> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categorie[position], callback)
    }

    override fun getItemCount(): Int {
        return categorie.size
    }

    fun updateList(categorie: List<Category>) {
        this.categorie = categorie
        notifyDataSetChanged()
    }


    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(data: Category, callback: (Category) -> Unit) {
            with(itemView) {
                val txtCategory = findViewById<Chip>(R.id.txtCategory)


                txtCategory.isChecked = data.isSelected
                txtCategory.setOnClickListener {

                    data.isSelected = !data.isSelected
                    callback.invoke(data)
                }


                txtCategory.text = data.category

            }
        }
    }


}