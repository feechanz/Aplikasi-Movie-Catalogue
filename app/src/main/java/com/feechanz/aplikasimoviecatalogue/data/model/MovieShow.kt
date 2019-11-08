package com.feechanz.aplikasimoviecatalogue.data.model

import android.os.Parcelable
import com.feechanz.aplikasimoviecatalogue.data.network.response.MovieResponse
import com.feechanz.aplikasimoviecatalogue.data.network.response.TvShowResponse
import kotlinx.android.parcel.Parcelize

/**
 * Created by Feechan on 11/7/2019.
 */
@Parcelize
data class MovieShow(
    var id:Long,
    var title:String?,
    var overview:String?,
    var releaseDate:String?,
    var posterPath:String?,
    var voteAverage:Double,
    var isMovie: Boolean
) : Parcelable {
    companion object {
        fun getInstance(movie: MovieResponse): MovieShow {
            return MovieShow(
                movie.id, movie.title, movie.overview, movie.releaseDate, movie
                    .posterPath, movie.voteAverage, true
            )
        }

        fun getInstance(show: TvShowResponse): MovieShow {
            return MovieShow(show.id, show.name, show.overview, show.firstAirDate, show
                .posterPath, show.voteAverage, false)
        }
    }
}