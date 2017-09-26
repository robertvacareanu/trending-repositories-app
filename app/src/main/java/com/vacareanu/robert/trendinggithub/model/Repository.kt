package com.vacareanu.robert.trendinggithub.model

import java.util.*

/**
 * Created by robert on 9/26/17.
 */
data class Repository(var url: String,
                      val name: String,
                      val created: Date,
                      val updated: Date,
                      val size: Int,
                      val languages: MutableList<String>,
                      var forks: Int,
                      var stars: Int)