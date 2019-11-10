package com.feechanz.favoriteapp

import android.content.ContentResolver
import com.feechanz.favoriteapp.model.MovieShow

/**
 * Created by Feechan on 11/10/2019.
 */
interface MainContract{
    interface View {
        fun showMovies(movies: List<MovieShow>)
    }

    interface Presenter {
        fun loadMovie(contentResolver: ContentResolver)
    }
}