package com.vacareanu.robert.trendinggithub.ui.details

import com.vacareanu.robert.trendinggithub.BaseViewModel
import com.vacareanu.robert.trendinggithub.model.Repository


class DetailsViewModel : BaseViewModel("DetailsViewModel") {
    lateinit var repo: Repository

    fun init(repo: Repository) {
        logV("Init")
        this.repo = repo
    }
}