package com.feechanz.aplikasimoviecatalogue.data.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Feechan on 11/7/2019.
 */
class TvShowResponse {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("name")
    var name: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("first_air_date")
    var firstAirDate: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
}