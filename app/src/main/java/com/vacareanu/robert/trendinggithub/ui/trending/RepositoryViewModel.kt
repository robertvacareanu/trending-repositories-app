package com.vacareanu.robert.trendinggithub.ui.repositories

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.persistence.room.Room
import android.os.Handler
import com.vacareanu.robert.trendinggithub.db.AppDatabase
import com.vacareanu.robert.trendinggithub.db.RepositoryDao
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.randomRepo


class RepositoryViewModel(application: Application) : AndroidViewModel(application) {

    var repositories: MutableLiveData<List<Repository>> = MutableLiveData()

    init {
        //val dao: RepositoryDao = Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "database-name").build().repositoryDao()
        repositories.value = listOf(
                randomRepo(), randomRepo()
        )
        val handler = Handler()
        handler.postDelayed({
            repositories.value = listOf(
                    randomRepo(), randomRepo(), randomRepo(), randomRepo()
            )
        }, 5000)
        //repositories.value = dao.getAll()
    }


}