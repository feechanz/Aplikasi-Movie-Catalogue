package com.feechanz.aplikasimoviecatalogue.data.model

import android.os.Parcelable
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
    var voteAverage:Double
) : Parcelable