package com.vacareanu.robert.trendinggithub.ui.repositories

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.vacareanu.robert.trendinggithub.JobExecutor
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.network.ApiResponse
import com.vacareanu.robert.trendinggithub.network.GithubService
import com.vacareanu.robert.trendinggithub.network.GithubServiceResponse
import java.text.SimpleDateFormat
import java.util.*


class RepositoryViewModel(application: Application) : AndroidViewModel(application) {

    var repositories: MutableLiveData<List<Repository>> = MutableLiveData()

    // Responsible for merging data in db and data from network
    init {
        repositories = transformationFromApiResponseToList(
                GithubService.githubNetwork.create(GithubService::class.java)
                        .getRepositories("language=kotlin+created%3A>${SimpleDateFormat("yyyy-MM-dd")
                                .format(Date().time - 14 * 24 * 60 * 60 * 1000)}", "stars")
        )

    }


    private fun transformationFromApiResponseToList(source: LiveData<ApiResponse<GithubServiceResponse>>): MutableLiveData<List<Repository>> {
        val result = MediatorLiveData<List<Repository>>()

        result.addSource(source) { t ->
            result.value = t?.body?.repositories?.map { it.toRepository() }
        }
        return result
    }




}