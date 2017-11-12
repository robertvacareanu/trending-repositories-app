package com.vacareanu.robert.trendinggithub.network

import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories")
    fun getRepositories(@Query(value = "q", encoded = true) query: String,
                        @Query(value = "sort", encoded = true) sort: String,
                        @Query(value = "order", encoded = true) order: String = "desc"): LiveData<ApiResponse<GithubServiceResponse>>


    companion object {
        val githubNetwork: Retrofit by lazy { Retrofit.Builder().baseUrl("https://api.github.com/").addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(LiveDataCallAdapterFactory()).build() }
    }
}