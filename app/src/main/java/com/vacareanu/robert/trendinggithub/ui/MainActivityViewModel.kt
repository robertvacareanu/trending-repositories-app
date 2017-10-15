package com.vacareanu.robert.trendinggithub.ui

import com.vacareanu.robert.trendinggithub.BaseViewModel
import com.vacareanu.robert.trendinggithub.JobExecutor
import com.vacareanu.robert.trendinggithub.db.RepositoryDao
import com.vacareanu.robert.trendinggithub.model.Repository


class MainActivityViewModel(private val repositoryDao: RepositoryDao) : BaseViewModel("MainActivityViewModel") {

    fun save(repo: Repository) =
            JobExecutor.execute {
                repositoryDao.insertAll(listOf(repo))
            }


}