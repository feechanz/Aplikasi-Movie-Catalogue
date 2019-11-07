package com.feechanz.aplikasimoviecatalogue.presentation.movie

import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow


/**
 * Created by Feechan on 11/1/2019.
 */
interface MovieContract{
    interface View{
        fun showMovies(movies: ArrayList<MovieShow>)
    }
    interface Presenter{
        fun loadMovie()
    }
}