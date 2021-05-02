package com.devvailonge.detail.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.devvailonge.detail.R
import com.devvailonge.detail.presentation.DetailFragment.DetailFilm

class DetailAdapter(private val detailFilms: List<DetailFilm>) :
    RecyclerView.Adapter<DetailAdapter.DetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_cast_movie, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
       val data = detailFilms[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
       return detailFilms.size
    }

    class DetailViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: DetailFilm) {
            with(itemView) {
                val imActors = findViewById<ImageView>(R.id.imActors)

                imActors.load(data.imageUrl) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(15f))
                }
            }
        }
    }
}