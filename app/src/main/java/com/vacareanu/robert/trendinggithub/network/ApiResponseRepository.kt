package com.vacareanu.robert.trendinggithub.network

import com.vacareanu.robert.trendinggithub.model.Repository
import java.util.*

data class ApiResponseRepository(
        val id: Int,
        val name: String,
        val html_url: String,
        val language: String,
        val forks: Int,
        val created_at: Date,
        val stars: Int,
        val description: String
) {
    fun toRepository(): Repository =
            with(Repository()) {
                url = this@ApiResponseRepository.html_url
                forks = this@ApiResponseRepository.forks
                stars = this@ApiResponseRepository.stars
                name = this@ApiResponseRepository.name
                created = this@ApiResponseRepository.created_at
                description = this@ApiResponseRepository.description
                this
            }
}