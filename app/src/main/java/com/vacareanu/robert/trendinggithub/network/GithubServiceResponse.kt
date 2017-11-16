package com.vacareanu.robert.trendinggithub.network

import com.google.gson.annotations.SerializedName

class GithubServiceResponse {
    @SerializedName("total_count")
    var total: Int? = null

    @SerializedName("items")
    var repositories: List<ApiResponseRepository>? = null

    @SerializedName("incomplete_results")
    var complete: Boolean? = null
}