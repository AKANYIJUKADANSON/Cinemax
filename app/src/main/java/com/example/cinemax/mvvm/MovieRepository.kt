package com.example.cinemax.mvvm

import android.database.Observable
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cinemax.MainActivity
import com.example.cinemax.models.Movie
import com.example.cinemax.models.MovieResponse
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

    fun getRoomMovies() = movieDatabase.getMovieDAO().getAllMovies()

    suspend fun insertMovieToDatabase(movieToInsert:MovieEntity) = movieDatabase.getMovieDAO().insertMovies(movieToInsert)


    // Getting the movies from API using retrofit
    fun getPopularMovies(page: Int) {
        val client = movieAPI.getPopularMovies(Constants.API_KEY, page)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {

                    // Insert whatever we get from api into the database
                    response.body()?.movies?.forEach {
                        suspend { insertMovieToDatabase(it) }

                    }
                    Log.e("List Of MOVIES From API", response.body()!!.movies.toString())
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("Error while getting movies from API", t.message.toString())
            }

        })
    }

}