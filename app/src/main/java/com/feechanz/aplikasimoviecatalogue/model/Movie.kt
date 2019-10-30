package com.feechanz.aplikasimoviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Feechan on 10/30/2019.
 */
@Parcelize
data class Movie(
    var photoUrl : String,
    var name: String,
    var description: String
) : Parcelable