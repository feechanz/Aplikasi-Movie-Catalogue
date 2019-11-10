package com.feechanz.favoriteapp.provider

import android.net.Uri

/**
 * Created by Feechan on 11/10/2019.
 */
object ContentProviderContract {
    const val AUTHORITY = "com.feechanz.aplikasimoviecatalogue"
    const val BASE_PATH = "movies"
    const val SCHEME = "content"

    const val MOVIE_ID_COLUMN: String = "movieId"
    const val TITLE_COLUMN: String = "title"
    const val OVERVIEW_COLUMN: String = "overview"
    const val RELEASE_DATE_COLUMN: String = "releaseDate"
    const val POSTER_PATH_COLUMN: String = "posterPath"
    const val VOTE_AVERAGE_COLUMN: String = "voteAverage"
    const val IS_MOVIE_COLUMN: String = "isMovie"

    val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(BASE_PATH)
        .build()
}