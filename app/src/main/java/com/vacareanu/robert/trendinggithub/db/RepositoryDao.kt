package com.vacareanu.robert.trendinggithub.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.vacareanu.robert.trendinggithub.model.Repository


@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repository")
    fun findAll(): LiveData<List<Repository>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(repositories: List<Repository>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(repository: Repository)

    @Delete
    fun delete(repository: Repository)

}