package com.vacareanu.robert.trendinggithub.db

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by robert on 10/7/17.
 */
class DateConverter {

    @TypeConverter
    fun fromString(string: String): Date = Date(string.toLong())

    @TypeConverter
    fun toString(date: Date): String = date.time.toString()

}