package com.feechanz.aplikasimoviecatalogue.data.network.retrofit

import com.feechanz.aplikasimoviecatalogue.data.network.response.*
import com.feechanz.aplikasimoviecatalogue.utils.Constant
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Url

/**
 * Created by Feechan on 11/8/2019.
 */
interface RetrofitRestApi {
    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getMovies(
        @Url url: String, @Query("api_key") apiKey: String,
        @Query("language") languageCode: String
    ):
            Call<ListResponse<MovieResponse>>

    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getMoviesReleaseToday(
        @Url url: String, @Query("api_key") apiKey: String,
        @Query("primary_release_date.gte") todayDate: String,
        @Query("primary_release_date.lte") todayDate2: String
    ):
            Call<ListResponse<MovieResponse>>

    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getTvShows(
        @Url url: String, @Query("api_key") apiKey: String,
        @Query("language") languageCode: String
    ):
            Call<ListResponse<TvShowResponse>>

    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getMovieDetail(
        @Url url: String, @Query("api_key") apiKey: String,
        @Query("language") languageCode: String
    ):
            Call<MovieDetailResponse>

    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getTvShowDetail(
        @Url url: String, @Query("api_key") apiKey: String,
        @Query("language") languageCode: String
    ):
            Call<TvShowDetailResponse>

    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getMoviesSearch(
        @Url url: String, @Query("api_key") apiKey: String,
        @Query("language") languageCode: String,
        @Query("query") query: String
    ):
            Call<ListResponse<MovieResponse>>

    @Headers(Constant.REQUEST_HEADER_CONTENT_TYPE)
    @GET
    fun getTvShowsSearch(
        @Url url: String, @Query("api_key") apiKey: String,
        @Query("language") languageCode: String,
        @Query("query") query: String
    ):
            Call<ListResponse<TvShowResponse>>
}