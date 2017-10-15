package com.vacareanu.robert.trendinggithub.db

import android.arch.persistence.room.TypeConverter


class ListConverter {

    @TypeConverter
    fun fromString(string: String): List<String> = string.split(", ")

    @TypeConverter
    fun toString(strings: List<String>): String = strings.joinToString(", ")

}