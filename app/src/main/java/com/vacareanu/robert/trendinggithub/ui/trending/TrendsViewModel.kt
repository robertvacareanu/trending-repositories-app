package com.vacareanu.robert.trendinggithub.ui.repositories

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import com.vacareanu.robert.trendinggithub.db.AppDatabase
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.network.ApiResponse
import com.vacareanu.robert.trendinggithub.network.GithubService
import com.vacareanu.robert.trendinggithub.network.GithubServiceResponse
import java.text.SimpleDateFormat
import java.util.*


class TrendsViewModel(application: Application) : AndroidViewModel(application) {


    val repositories by lazy { MediatorLiveData<List<Repository>>() }
    private var networkRepositories: MutableLiveData<List<Repository>> = MutableLiveData()
    private var roomRepositories: LiveData<List<Repository>>

    // Responsible for merging data in db and data from network
    init {
        roomRepositories = Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "database-name").build().repositoryDao().findAll()
        networkRepositories = transformationFromApiResponseToList(
                GithubService.githubNetwork.create(GithubService::class.java)
                        .getRepositories("language=kotlin+created%3A>${SimpleDateFormat("yyyy-MM-dd")
                                .format(Date().time - 14 * 24 * 60 * 60 * 1000)}", "stars")
        )
        repositories.addSource(networkRepositories) { t: List<Repository>? ->
            repositories.value = t
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