package com.example.cinemax.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface MovieDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movie:MovieEntity)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()

}

