package com.vacareanu.robert.trendinggithub

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import com.vacareanu.robert.trendinggithub.db.AppDatabase
import com.vacareanu.robert.trendinggithub.ui.MainActivityViewModel

/**
 * Created by robert on 10/6/17.
 */
class MainActivityViewModelFactory(val applicationContext: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {

        return MainActivityViewModel(Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build().repositoryDao()) as T

    }
}