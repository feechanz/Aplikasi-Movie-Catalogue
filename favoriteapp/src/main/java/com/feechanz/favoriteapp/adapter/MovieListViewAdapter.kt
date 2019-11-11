package com.feechanz.favoriteapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.feechanz.favoriteapp.R
import com.feechanz.favoriteapp.model.MovieShow
import com.feechanz.favoriteapp.utils.Constant
import com.squareup.picasso.Picasso
import java.lang.Exception

/**
 * Created by Feechan on 10/30/2019.
 */

class MovieListViewAdapter : RecyclerView
.Adapter<MovieListViewAdapter.ViewHolder>() {
    private var movies = arrayListOf<MovieShow>()

    fun addAll(movies: List<MovieShow>) {
        this.movies.clear()
        this.movies.addAll(movies)
        try {
            notifyDataSetChanged()
        }catch (ex: Exception){
            ex.printStackTrace()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.row_movie_layout, parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    var onMovieClick: OnMovieClick? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val rootLayout: ViewGroup = view.findViewById(R.id.rootLayout)
        private val photoImageView: ImageView = view.findViewById(R.id.photoImageView)
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)

        internal fun bind(movie: MovieShow) {
            Picasso.get()
                .load(Constant.getPictureFullPath() + movie.posterPath)
                .into(photoImageView)
            nameTextView.text = movie.title
            descriptionTextView.text = movie.overview

            rootLayout.setOnClickListener { onMovieClick?.onClick(movie) }
        }
    }

    interface OnMovieClick {
        fun onClick(movie: MovieShow)
    }
}