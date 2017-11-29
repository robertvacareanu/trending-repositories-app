package com.vacareanu.robert.trendinggithub

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import com.vacareanu.robert.trendinggithub.db.AppDatabase
import com.vacareanu.robert.trendinggithub.ui.MainActivityViewModel
import com.vacareanu.robert.trendinggithub.ui.favorites.FavoritesViewModel
import com.vacareanu.robert.trendinggithub.ui.repositories.TrendsViewModel


class FavoritesViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {

        return FavoritesViewModel(Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build().repositoryDao()) as T

    }
}

class TrendsViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        return TrendsViewModel(Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build().repositoryDao()) as T
    }
}

class MainActivityViewModelFactory(private val applicationContext: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {

        return MainActivityViewModel(Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name").build().repositoryDao()) as T

    }
}
