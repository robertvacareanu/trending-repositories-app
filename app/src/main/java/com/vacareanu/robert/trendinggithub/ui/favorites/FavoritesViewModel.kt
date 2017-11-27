package com.vacareanu.robert.trendinggithub.ui.favorites

import android.arch.lifecycle.LiveData
import android.content.Intent
import android.net.Uri
import com.vacareanu.robert.trendinggithub.BaseViewModel
import com.vacareanu.robert.trendinggithub.JobExecutor
import com.vacareanu.robert.trendinggithub.db.RepositoryDao
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.ui.repositories.GithubTrendsRV


class FavoritesViewModel(val repository: RepositoryDao) : BaseViewModel("TrendsViewModel") {


    var repositories: LiveData<List<Repository>>

    init {
        repositories = repository.findAll()
    }

    fun handleRepoClick(position: Int, view: FavoriteView) {
        //launch browser for url at position
        view.launchPage(repositories.value!![position].url)

    }

    fun handleHeartClick(position: Int, view: GithubTrendsRV.OnGithubTrendsRVInteractionListener) {

    }


}