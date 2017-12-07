package com.vacareanu.robert.trendinggithub.ui.repositories

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.vacareanu.robert.trendinggithub.BaseViewModel
import com.vacareanu.robert.trendinggithub.db.RepositoryDao
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.network.ApiResponse
import com.vacareanu.robert.trendinggithub.network.GithubService
import com.vacareanu.robert.trendinggithub.network.GithubServiceResponse
import java.text.SimpleDateFormat
import java.util.*


class TrendsViewModel(val repository: RepositoryDao) : BaseViewModel("TrendsViewModel") {


    val repositories by lazy { MediatorLiveData<List<Repository>>() }
    private var networkRepositories: MutableLiveData<List<Repository>> = MutableLiveData()
    private var roomRepositories: LiveData<List<Repository>>

    // Responsible for merging data in db and data from network
    init {
        Log.v("TVM", "Init")
        roomRepositories = repository.findAll()
        networkRepositories = transformationFromApiResponseToList(
                GithubService.githubNetwork.create(GithubService::class.java)
                        .getRepositories("language=kotlin+created%3A>${SimpleDateFormat("yyyy-MM-dd")
                                .format(Date().time - 14 * 24 * 60 * 60 * 1000)}", "stars")
        )

        repositories.addSource(networkRepositories) { t: List<Repository>? ->
            t?.let {
                t
                        .filter { r -> roomRepositories.value?.firstOrNull { r.url == it.url } != null }
                        .forEach { it.isFavorite = true }
            }
            repositories.value = t
        }
        repositories.addSource(roomRepositories) { t: List<Repository>? ->
            val repositoriesResult = repositories.value.orEmpty()
            t?.let {
                repositoriesResult.forEach {
                    it.isFavorite = false
                }
                t.forEach { r ->
                    repositoriesResult.firstOrNull { r.url == it.url }?.let { it.isFavorite = true }
                }
                repositoriesResult.forEach { if (it.isFavorite) Log.v("TVM", "Is favorite: ${it.url}") }
            }
            repositories.value = repositoriesResult
        }


    }


    private fun transformationFromApiResponseToList(source: LiveData<ApiResponse<GithubServiceResponse>>): MutableLiveData<List<Repository>> {
        val result = MediatorLiveData<List<Repository>>()

        result.addSource(source) { t ->
            result.value = t?.body?.repositories?.map { it.toRepository() }
        }
        return result
    }

}