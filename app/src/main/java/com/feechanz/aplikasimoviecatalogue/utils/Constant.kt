package com.feechanz.aplikasimoviecatalogue.utils

/**
 * Created by Feechan on 11/7/2019.
 */
class Constant() {
    companion object {
        const val CONNECTION_TIMEOUT:Long = 60
        const val READ_TIMEOUT:Long = 60

        const val REQUEST_HEADER_CONTENT_TYPE:String = "Content-Type: application/json"

        const val PICTURE_BASE_PATH = "https://image.tmdb.org/t/p/"
        const val PICTURE_SIZE = "w185"
        fun getPictureFullPath() : String = PICTURE_BASE_PATH + PICTURE_SIZE
        const val SERVER_URL = "https://api.themoviedb.org/"
        const val SERVER_SUB_URL = "3/discover/"
        fun getServerFullUrl() : String = SERVER_URL + SERVER_SUB_URL
    }
}