package com.vacareanu.robert.trendinggithub.network

import com.google.gson.annotations.SerializedName

/**
 * Created by robert on 10/28/17.
 */
class GithubServiceResponse {
    @SerializedName("total_count")
    var total: Int? = null

    @SerializedName("items")
    var repositories: List<ApiResponseRepository>? = null

    @SerializedName("incomplete_results")
    var complete: Boolean? = null
}