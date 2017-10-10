package com.vacareanu.robert.trendinggithub.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.vacareanu.robert.trendinggithub.model.Repository

/**
 * Created by robert on 10/6/17.
 */
@Dao
interface RepositoryDao {

    @Query("SELECT * FROM repository")
    fun findAll(): LiveData<List<Repository>>

    @Query("SELECT * FROM repository")
    fun getAll(): List<Repository>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notes: List<Repository>)

    @Delete
    fun delete(n: Repository)

}