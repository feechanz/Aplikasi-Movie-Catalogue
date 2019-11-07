package com.feechanz.aplikasimoviecatalogue.data

import android.content.Context
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApi
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApiImpl

/**
 * Created by Feechan on 11/1/2019.
 */
class MovieRepository(val context: Context?){
    private var restApi: RestApi = RestApiImpl()

    fun getMovies() : ArrayList<MovieShow> {
        val movies = arrayListOf<MovieShow>()
        return movies
    }
}