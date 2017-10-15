package com.vacareanu.robert.trendinggithub.ui.favorites

import android.arch.lifecycle.LiveData
import com.vacareanu.robert.trendinggithub.BaseViewModel
import com.vacareanu.robert.trendinggithub.db.RepositoryDao
import com.vacareanu.robert.trendinggithub.model.Repository


class FavoritesViewModel(repository: RepositoryDao) : BaseViewModel("RepositoryViewModel") {


    val repositories: LiveData<List<Repository>>

    init {
        logV("Init")
        repositories = repository.findAll()
        logV("Init finished")
        logV("Size ${repositories.value?.size.toString()}")
    }


}