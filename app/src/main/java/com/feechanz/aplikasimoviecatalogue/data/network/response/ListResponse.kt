package com.feechanz.aplikasimoviecatalogue.data.network.response

import com.google.gson.annotations.SerializedName


/**
 * Created by Feechan on 11/8/2019.
 */
class ListResponse<T> {
    @SerializedName("page")
    var page: Int? = null
    @SerializedName("total_results")
    var totalResults: Int? = null
    @SerializedName("total_pages")
    var totalPages: Int? = null
    @SerializedName("results")
    var results: List<T>? = null

}