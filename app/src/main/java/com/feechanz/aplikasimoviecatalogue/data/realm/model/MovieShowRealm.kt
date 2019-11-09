package com.feechanz.aplikasimoviecatalogue.data.realm.model

import com.feechanz.aplikasimoviecatalogue.data.model.MovieShow
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by Feechan on 11/9/2019.
 */
open class MovieShowRealm(
    @PrimaryKey open var id: Long = 0,
    open var movieId: Long = 0,
    open var title: String? = null,
    open var overview: String? = null,
    open var releaseDate: String? = null,
    open var posterPath: String? = null,
    open var voteAverage: Double = 0.0,
    open var isMovie: Boolean = false
) : RealmObject() {
    companion object {
        const val ID_COLUMN: String = "id"
        const val MOVIE_ID_COLUMN: String = "movieId"
        const val TITLE_COLUMN: String = "title"
        const val OVERVIEW_COLUMN: String = "overview"
        const val RELEASE_DATE_COLUMN: String = "releaseDate"
        const val POSTER_PATH_COLUMN: String = "posterPath"
        const val VOTE_AVERAGE_COLUMN: String = "voteAverage"
        const val IS_MOVIE_COLUMN: String = "isMovie"
        fun getInstance(id: Long, movie: MovieShow): MovieShowRealm {
            return MovieShowRealm(
                id,
                movie.id,
                movie.title,
                movie.overview,
                movie.releaseDate,
                movie.posterPath,
                movie.voteAverage,
                movie.isMovie
            )
        }
    }

    fun transform(): MovieShow {
        return MovieShow(movieId, title, overview, releaseDate, posterPath, voteAverage, isMovie)
    }
}