package com.feechanz.aplikasimoviecatalogue.data.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Feechan on 11/9/2019.
 */
class TvShowDetailResponse {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("status")
    var status: String? = null
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