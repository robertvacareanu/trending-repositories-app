package com.vacareanu.robert.trendinggithub.ui.repositories

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.os.Handler
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.randomRepo


class RepositoryViewModel(application: Application) : AndroidViewModel(application) {

    var repositories: MutableLiveData<List<Repository>> = MutableLiveData()

    init {
        repositories.value = listOf(
                randomRepo(), randomRepo()
        )
        val handler = Handler()
        handler.postDelayed({
            repositories.value = listOf(
                    randomRepo(), randomRepo(), randomRepo(), randomRepo()
            )
        }, 5000)
    }


}