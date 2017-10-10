package com.vacareanu.robert.trendinggithub.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.vacareanu.robert.trendinggithub.model.Repository

/**
 * Created by robert on 10/2/17.
 */
@Database(entities = arrayOf(Repository::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun repositoryDao():RepositoryDao
}