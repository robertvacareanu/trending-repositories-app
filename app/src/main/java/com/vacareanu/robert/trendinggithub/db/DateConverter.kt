package com.vacareanu.robert.trendinggithub.db

import android.arch.persistence.room.TypeConverter
import java.util.*


class DateConverter {

    @TypeConverter
    fun fromString(string: String): Date = Date(string.toLong())

    @TypeConverter
    fun toString(date: Date): String = date.time.toString()

}