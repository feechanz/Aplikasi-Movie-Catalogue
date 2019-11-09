package com.feechanz.aplikasimoviecatalogue.utils

/**
 * Created by Feechan on 11/7/2019.
 */
class Constant {
    companion object {
        const val CONNECTION_TIMEOUT: Long = 60
        const val READ_TIMEOUT: Long = 60
        const val API_KEY: String = "9ae7cd4ecc327fbfe281cd2aec3430c9"

        const val REQUEST_HEADER_CONTENT_TYPE: String = "Content-Type: application/json"

        const val PICTURE_BASE_PATH = "https://image.tmdb.org/t/p/"
        const val PICTURE_SIZE = "w185"
        fun getPictureFullPath(): String = PICTURE_BASE_PATH + PICTURE_SIZE

        const val SERVER_URL = "https://api.themoviedb.org/"
        fun getServerFullUrl(): String = SERVER_URL

        const val GET_MOVIES = "3/discover/movie"
        const val GET_TV_SHOW = "3/discover/tv"
        const val GET_MOVIE_DETAIL = "3/movie/"
        const val GET_TV_SHOW_DETAIL = "3/tv/"
        fun getEndpointPath(endpoint: String): String {
            return getServerFullUrl() + endpoint
        }
    }
}