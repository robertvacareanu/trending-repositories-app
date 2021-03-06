package com.vacareanu.robert.trendinggithub.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.vacareanu.robert.trendinggithub.db.DateConverter
import com.vacareanu.robert.trendinggithub.db.ListConverter
import java.util.*

//@Entity(tableName = "repository")
//data class Repository(@PrimaryKey var url: String = "",
//                      var name: String = "",
//                      var description: String = "",
//                      @TypeConverters(DateConverter::class) var created: Date = Date(),
//                      @TypeConverters(DateConverter::class) var updated: Date = Date(),
//                      var size: Int = 0,
//                      @TypeConverters(ListConverter::class) var languages: MutableList<String> = mutableListOf(),
//                      var forks: Int = 0,
//                      var stars: Int = 0,
//                      var isFavorite: Boolean = false) {
//
//    constructor() : this("", "", "", Date(), Date(), 0, mutableListOf(), 0, 0, false)
//
//}

@Entity(tableName = "repository")
class Repository {
    @PrimaryKey
    var url: String = ""
    var name: String = ""
    var description: String = ""
    @TypeConverters(DateConverter::class)
    var created: Date = Date()
    @TypeConverters(DateConverter::class)
    var updated: Date = Date()
    var size: Int = 0
    @TypeConverters(ListConverter::class)
    var languages: MutableList<String> = mutableListOf()
    var forks: Int = 0
    var stars: Int = 0
    var isFavorite: Boolean = false


    override fun toString(): String = "Url: $url, name: $name, description: $description"
}