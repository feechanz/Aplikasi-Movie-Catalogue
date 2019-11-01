package com.feechanz.aplikasimoviecatalogue.presentation.movie

import com.feechanz.aplikasimoviecatalogue.model.Movie

/**
 * Created by Feechan on 11/1/2019.
 */
interface MovieContract{
    interface View{
        fun showMovies(movies: ArrayList<Movie>)
    }
    interface Presenter{
        fun loadMovie()
    }
}