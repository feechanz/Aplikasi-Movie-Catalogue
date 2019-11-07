package com.feechanz.aplikasimoviecatalogue.presentation.show

import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow

/**
 * Created by Feechan on 11/1/2019.
 */
interface ShowContract{
    interface View{
        fun showShows(shows: ArrayList<MovieShow>)
    }
    interface Presenter{
        fun loadShow()
    }
}