package com.example.cinemax.mvvm

import android.database.Observable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinemax.MainActivity
import com.example.cinemax.models.Movie
import com.example.cinemax.retrofit.MovieAPIService
import com.example.cinemax.roomdb.MovieDAO
import com.example.cinemax.roomdb.MovieDatabase
import com.example.cinemax.roomdb.MovieEntity
import com.example.cinemax.utils.Constants
import kotlinx.coroutines.GlobalScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(
    private val movieAPI:MovieAPIService,
    private val movieDAO: MovieDAO,
    private val movieDatabase: MovieDatabase
    ) {

    suspend fun insertMovieToDatabase(movieToInsert:MovieEntity) = movieDatabase.getMovieDAO().insertMovies(movieToInsert)

    fun getRoomMovies() = movieDatabase.getMovieDAO().getAllMovies()


    // Getting the movies from API using retrofit
//    fun getPopulars() =

}