package com.feechanz.aplikasimoviecatalogue.presentation.show

import com.feechanz.aplikasimoviecatalogue.model.Movie

/**
 * Created by Feechan on 11/1/2019.
 */
interface ShowContract{
    interface View{
        fun showShows(movies: ArrayList<Movie>)
    }
    interface Presenter{
        fun loadShow()
    }
}