package com.vacareanu.robert.trendinggithub.ui.details

import com.vacareanu.robert.trendinggithub.model.Repository
import com.vacareanu.robert.trendinggithub.ui.BaseViewModel

/**
 * Created by robert on 10/1/17.
 */
class DetailsViewModel : BaseViewModel("DetailsViewModel") {
    lateinit var repo: Repository

    fun init(repo: Repository) {
        this.repo=repo
    }
}