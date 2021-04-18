package com.devvailonge.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.devvailonge.home.R
import com.devvailonge.home.domain.Series
import com.devvailonge.home.presentation.FilmAdapter.FilmViewHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi

class FilmAdapter() :
    ListAdapter<Series, FilmViewHolder>(FilmAdapter) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_film, parent, false)
        return FilmAdapter.FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }




    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @ExperimentalCoroutinesApi
        fun bind(data: Series) {
            with(itemView) {

                val ivFilm = findViewById<ImageView>(R.id.ivFilm)
                val txtFilm = findViewById<TextView>(R.id.txtFilm)
                txtFilm.text = data.name


                ivFilm.load(data.image.original) {
                    crossfade(true)
                    transformations(RoundedCornersTransformation(15f))
                }
            }
        }
    }

        companion object : DiffUtil.ItemCallback<Series>() {
            override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
                return oldItem == newItem
            }

        }
    }





