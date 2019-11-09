package com.feechanz.aplikasimoviecatalogue.data.model

import android.os.Parcelable
import com.feechanz.aplikasimoviecatalogue.data.network.response.MovieDetailResponse
import com.feechanz.aplikasimoviecatalogue.data.network.response.TvShowDetailResponse
import kotlinx.android.parcel.Parcelize

/**
 * Created by Feechan on 11/9/2019.
 */
@Parcelize
data class MovieShowDetail(
    var id: Long,
    var status: String?,
    var title: String?,
    var overview: String?,
    var releaseDate: String?,
    var posterPath: String?,
    var voteAverage: Double,
    var isMovie: Boolean
) : Parcelable {
    companion object {
        fun getInstance(movie: MovieDetailResponse): MovieShowDetail {
            return MovieShowDetail(
                movie.id,
                movie.status,
                movie.title,
                movie.overview,
                movie.releaseDate,
                movie.posterPath,
                movie.voteAverage,
                true
            )
        }

        fun getInstance(show: TvShowDetailResponse): MovieShowDetail {
            return MovieShowDetail(
                show.id,
                show.status,
                show.name,
                show.overview,
                show.firstAirDate,
                show.posterPath,
                show.voteAverage,
                false
            )
        }
    }
}