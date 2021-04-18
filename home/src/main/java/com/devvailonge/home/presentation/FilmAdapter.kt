package com.devvailonge.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.devvailonge.home.R
import com.devvailonge.home.presentation.FilmAdapter.FilmViewHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi

class FilmAdapter (private val films: List<HomeFragment.Film>)
    : RecyclerView.Adapter<FilmViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_film, parent, false)
        return FilmAdapter.FilmViewHolder(view)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.bind(films[position])
    }

    override fun getItemCount(): Int {
        return films.size
    }



    class FilmViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

        @ExperimentalCoroutinesApi
        fun bind (data: HomeFragment.Film) {
            with(itemView) {

            val ivFilm = findViewById<ImageView>(R.id.ivFilm)
            val txtFilm = findViewById<TextView>(R.id.txtFilm)
                txtFilm.text = data.nome

                    ivFilm.load(data.image) {
                        crossfade(true)
                        //transformations(RoundedCornersTransformation(topLeft = 15f, topRight = 15f))
                    }
            }
        }

    }


}


