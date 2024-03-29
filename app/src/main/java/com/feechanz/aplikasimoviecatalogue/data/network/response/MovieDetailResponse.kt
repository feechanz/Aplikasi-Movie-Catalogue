package com.feechanz.aplikasimoviecatalogue.data.network.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Feechan on 11/9/2019.
 */
class MovieDetailResponse {
    @SerializedName("id")
    var id: Long = 0
    @SerializedName("status")
    var status: String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("release_date")
    var releaseDate: String? = null
    @SerializedName("poster_path")
    var posterPath: String? = null
    @SerializedName("vote_average")
    var voteAverage: Double = 0.0
}