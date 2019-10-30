package com.feechanz.aplikasimoviecatalogue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.feechanz.aplikasimoviecatalogue.R
import com.feechanz.aplikasimoviecatalogue.model.Movie
import com.squareup.picasso.Picasso

/**
 * Created by Feechan on 10/30/2019.
 */

class MovieListViewAdapter internal constructor(private val context: Context) : BaseAdapter(){
    internal var movies = arrayListOf<Movie>()

    var onMovieClick: OnMovieClick? = null

    override fun getItem(i: Int): Any = movies[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getCount(): Int = movies.size



    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        var itemView = view
        if(itemView == null){
            itemView = LayoutInflater.from(context).inflate(R.layout.row_movie_layout, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val movie = getItem(position) as Movie
        viewHolder.bind(movie)
        return itemView
    }

    private inner class ViewHolder internal constructor(view: View) {
        private val rootLayout: ViewGroup = view.findViewById(R.id.rootLayout)
        private val photoImageView: ImageView = view.findViewById(R.id.photoImageView)
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val descriptionTextView: TextView = view.findViewById(R.id.descriptionTextView)

        internal fun bind(movie: Movie){
            Picasso.get().load(movie.photoUrl).into(photoImageView)
            nameTextView.text = movie.name
            descriptionTextView.text = movie.description

            rootLayout.setOnClickListener(View.OnClickListener { onMovieClick?.onClick(movie) })
        }
    }

    interface OnMovieClick{
        fun onClick(movie: Movie)
    }
}