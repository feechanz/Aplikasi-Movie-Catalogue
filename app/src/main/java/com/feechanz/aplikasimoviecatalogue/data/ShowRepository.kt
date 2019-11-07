package com.feechanz.aplikasimoviecatalogue.data

import android.content.Context
import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApi
import com.feechanz.aplikasimoviecatalogue.data.network.retrofit.RestApiImpl

/**
 * Created by Feechan on 11/1/2019.
 */
class ShowRepository(val context: Context?){
    private var restApi: RestApi = RestApiImpl()

    fun getShows() : ArrayList<MovieShow> {
        val shows = arrayListOf<MovieShow>()
        return shows
    }
}