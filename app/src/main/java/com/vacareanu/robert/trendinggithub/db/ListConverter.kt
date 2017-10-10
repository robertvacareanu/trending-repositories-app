package com.vacareanu.robert.trendinggithub.db

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by robert on 10/7/17.
 */
class ListConverter {

    @TypeConverter
    fun fromString(string: String): List<String> = string.split(", ")

    @TypeConverter
    fun toString(strings: List<String>): String = strings.joinToString(", ")

}