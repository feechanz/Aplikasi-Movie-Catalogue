package com.feechanz.aplikasimoviecatalogue.data.network.retrofit

import com.feechanz.aplikasimoviecatalogue.data.network.response.*
import retrofit2.Response

/**
 * Created by Feechan on 11/7/2019.
 */
interface RestApi {
    fun getMovies(url: String, apiKey: String, languageCode: String):
            Response<ListResponse<MovieResponse>>

    fun getMoviesReleaseToday(url: String, apiKey: String, todayDate: String, todayDate2:String):
            Response<ListResponse<MovieResponse>>

    fun getTvShows(url: String, apiKey: String, languageCode: String):
            Response<ListResponse<TvShowResponse>>

    fun getMoviesSearch(url: String, apiKey: String, languageCode: String, query: String):
            Response<ListResponse<MovieResponse>>

    fun getTvShowsSearch(url: String, apiKey: String, languageCode: String, query: String):
            Response<ListResponse<TvShowResponse>>

    fun getMovieDetail(url: String, apiKey: String, languageCode: String):
            Response<MovieDetailResponse>

    fun getTvShowDetail(url: String, apiKey: String, languageCode: String):
            Response<TvShowDetailResponse>
}