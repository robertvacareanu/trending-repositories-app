package com.vacareanu.robert.trendinggithub.ui.details

import android.arch.lifecycle.LiveData
import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.BaseViewModel

/**
 * Created by robert on 10/1/17.
 */
class DetailsViewModel : BaseViewModel("DetailsViewModel") {
    lateinit var repo: Repository

    fun init(repo: Repository) {
        logV("Init")
        this.repo=repo
    }
}