package com.feechanz.aplikasimoviecatalogue.data.network.retrofit

import com.feechanz.aplikasimoviecatalogue.data.network.response.*
import com.feechanz.aplikasimoviecatalogue.utils.Constant
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Feechan on 11/8/2019.
 */
class RestApiImpl : RestApi {


    private lateinit var apiService: RetrofitRestApi

    init {
        buildRetrofit()
    }

    fun buildRetrofit() {
        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(Constant.getServerFullUrl())
            .client(createHttpClient())
            .build()

        apiService = retrofit.create(RetrofitRestApi::class.java)
    }

    override fun getMovies(url: String, apiKey: String, languageCode: String):
            Response<ListResponse<MovieResponse>> {
        return apiService.getMovies(url, apiKey, languageCode).execute()
    }

    override fun getMoviesReleaseToday(
        url: String,
        apiKey: String,
        todayDate: String,
        todayDate2: String
    ): Response<ListResponse<MovieResponse>> {
        return apiService.getMoviesReleaseToday(url, apiKey, todayDate, todayDate2).execute()
    }

    override fun getTvShows(url: String, apiKey: String, languageCode: String):
            Response<ListResponse<TvShowResponse>> {
        return apiService.getTvShows(url, apiKey, languageCode).execute()
    }

    override fun getMoviesSearch(
        url: String,
        apiKey: String,
        languageCode: String,
        query: String
    ): Response<ListResponse<MovieResponse>> {
        return apiService.getMoviesSearch(url, apiKey, languageCode, query).execute()
    }

    override fun getTvShowsSearch(
        url: String,
        apiKey: String,
        languageCode: String,
        query: String
    ): Response<ListResponse<TvShowResponse>> {
        return apiService.getTvShowsSearch(url, apiKey, languageCode, query).execute()
    }

    override fun getMovieDetail(
        url: String,
        apiKey: String,
        languageCode: String
    ): Response<MovieDetailResponse> {
        return apiService.getMovieDetail(url, apiKey, languageCode).execute()
    }

    override fun getTvShowDetail(
        url: String,
        apiKey: String,
        languageCode: String
    ): Response<TvShowDetailResponse> {
        return apiService.getTvShowDetail(url, apiKey, languageCode).execute()
    }

    private fun createHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        setTimeout(httpClient)
        setLogger(httpClient)
        return httpClient.build()
    }

    private fun setTimeout(okHttpClientBuilder: OkHttpClient.Builder) {
        okHttpClientBuilder.readTimeout(Constant.READ_TIMEOUT, TimeUnit.SECONDS)
        okHttpClientBuilder.connectTimeout(Constant.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
    }

    private fun setLogger(okHttpClientBuilder: OkHttpClient.Builder) {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(logging)
    }
}