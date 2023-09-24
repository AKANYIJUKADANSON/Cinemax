package com.example.cinemax.mvvm

import com.example.cinemax.roomdb.MovieDatabase
import com.example.cinemax.roomdb.MovieEntity

class MovieRepository( private val movieDatabase: MovieDatabase ) {

    suspend fun insertMovieToDatabase(movieToInsert:MovieEntity) = movieDatabase.getMovieDAO().insertMovies(movieToInsert)

    fun getRoomMovies() = movieDatabase.getMovieDAO().getAllMovies()

}