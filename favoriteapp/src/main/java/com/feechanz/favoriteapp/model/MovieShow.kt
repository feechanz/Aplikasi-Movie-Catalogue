package com.feechanz.favoriteapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Feechan on 11/10/2019.
 */
@Parcelize
data class MovieShow(
    var id: Long,
    var title: String?,
    var overview: String?,
    var releaseDate: String?,
    var posterPath: String?,
    var voteAverage: Double,
    var isMovie: Boolean
) : Parcelable