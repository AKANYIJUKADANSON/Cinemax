package com.example.cinemax.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemax.MainActivity
import com.example.cinemax.models.MovieResponse
import com.example.cinemax.retrofit.MovieAPIService
import com.example.cinemax.retrofit.RetrofitInstance
import com.example.cinemax.roomdb.MovieDatabase
import com.example.cinemax.roomdb.MovieEntity
import com.example.cinemax.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteViewModel( private val repository: MovieRepository, private val movieAPI: MovieAPIService):ViewModel() {

    fun insertMovieToDatabase(movieToInsert:MovieEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovieToDatabase(movieToInsert)
        }
    }

     // Get movies from the database
    fun getRoomMovies() = repository.getRoomMovies()

    // Get movies from the API/Retrofit call
//    fun getPopularMovies(){
//        repository.getPopularMovies(1)
//    }


    fun getPopularMovies(page: Int) {
        val client = movieAPI.getPopularMovies(Constants.API_KEY, page)
        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    Log.e("List Of MOVIES From API", response.body()!!.movies.toString())

                    // Insert whatever we get from api into the database
                    response.body()?.movies?.forEach {
                        Log.e("xxxxxxxxxxxx", "My movie -> ${it.title}")
                        val movieToInsert = MovieEntity(
                            it.id, it.title,it.overview,
                                // movie.poster_Path,
                                // movie.backdrop_Path,
                                // movie.rating, movie.release_Date
                        )
                        insertMovieToDatabase(movieToInsert)
//                        MainActivity().myMovie(it)
                    }

                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("Error while getting movies from API", t.message.toString())
            }
        })
    }
}