package com.feechanz.aplikasimoviecatalogue.data.network.retrofit

import com.feechanz.aplikasimoviecatalogue.data.network.response.ListResponse
import com.feechanz.aplikasimoviecatalogue.data.network.response.MovieResponse
import com.feechanz.aplikasimoviecatalogue.data.network.response.TvShowResponse
import retrofit2.Response

/**
 * Created by Feechan on 11/7/2019.
 */
interface RestApi{
    fun getMovies(url:String, apiKey:String, languageCode: String):
            Response<ListResponse<MovieResponse>>
    fun getTvShows(url:String, apiKey:String, languageCode: String):
            Response<ListResponse<TvShowResponse>>
}