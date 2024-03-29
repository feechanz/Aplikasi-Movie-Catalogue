package com.feechanz.aplikasimoviecatalogue.data.provider

/**
 * Created by Feechan on 11/9/2019.
 */
object ContentProviderContract {
    const val AUTHORITY = "com.feechanz.aplikasimoviecatalogue"
    const val BASE_PATH = "movies"

    const val MOVIE_ID_COLUMN: String = "movieId"
    const val TITLE_COLUMN: String = "title"
    const val OVERVIEW_COLUMN: String = "overview"
    const val RELEASE_DATE_COLUMN: String = "releaseDate"
    const val POSTER_PATH_COLUMN: String = "posterPath"
    const val VOTE_AVERAGE_COLUMN: String = "voteAverage"
    const val IS_MOVIE_COLUMN: String = "isMovie"
}