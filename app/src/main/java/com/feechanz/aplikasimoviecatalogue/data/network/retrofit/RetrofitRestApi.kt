package com.feechanz.aplikasimoviecatalogue.data.network.retrofit

import com.feechanz.aplikasimoviecatalogue.data.network.response.ListResponse
import com.feechanz.aplikasimoviecatalogue.data.network.response.MovieResponse
import com.feechanz.aplikasimoviecatalogue.data.network.response.TvShowResponse
import com.feechanz.aplikasimoviecatalogue.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Feechan on 11/8/2019.
 */
interface RetrofitRestApi{
    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getMovies(@Url url: String, @Query("api_key") apiKey: String):
            Call<ListResponse<MovieResponse>>

    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getTvShows(@Url url:String, @Query("api_key") apiKey: String):
            Call<ListResponse<TvShowResponse>>
}