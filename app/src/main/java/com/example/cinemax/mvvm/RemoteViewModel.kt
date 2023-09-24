package com.example.cinemax.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemax.MainActivity
import com.example.cinemax.models.MovieResponse
import com.example.cinemax.models.MyMovieListResponse
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


    fun getPopularMovies(page: Int) {
        val client = movieAPI.getPopularMovies(Constants.API_KEY, page)
        client.enqueue(object : Callback<MyMovieListResponse> {
            override fun onResponse(call: Call<MyMovieListResponse>, response: Response<MyMovieListResponse>) {
                if (response.isSuccessful) {
                    Log.e("List Of MOVIES From API", response.body()!!.results.toString())

                    // Insert whatever we get from api into the database
                    response.body()?.results?.forEach {
//                        Log.e("xxxxxxxxxxxx", "My movie -> ${it.title}")
                        val movieToInsert = MovieEntity(
                            it.adult, it.backdrop_path, it.id, it.original_language,
                            it.original_title, it.overview, it.popularity, it.poster_path,
                            it.release_date, it.title, it.video, it.vote_average, it.vote_count
                        )
                        // Inserting the data into room db
                        insertMovieToDatabase(movieToInsert)
                    }

                }
            }
            override fun onFailure(call: Call<MyMovieListResponse>, t: Throwable) {
                Log.e("Error while getting movies from API", t.message.toString())
            }
        })
    }
}