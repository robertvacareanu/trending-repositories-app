package com.vacareanu.robert.trendinggithub.ui.favorites

import android.arch.lifecycle.LiveData
import com.vacareanu.robert.trendinggithub.db.RepositoryDao
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.BaseViewModel

/**
 * Created by robert on 10/1/17.
 */
class FavoritesViewModel(repository: RepositoryDao) : BaseViewModel("RepositoryViewModel") {


    val repositories: LiveData<List<Repository>>

    init {
        logV("Init")
        repositories = repository.findAll()
        logV("Init finished")
        logV("Size ${repositories.value?.size.toString()}")
    }


}