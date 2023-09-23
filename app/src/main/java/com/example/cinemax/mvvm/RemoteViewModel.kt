package com.example.cinemax.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cinemax.models.MovieResponse
import com.example.cinemax.retrofit.RetrofitInstance
import com.example.cinemax.roomdb.MovieDatabase
import com.example.cinemax.roomdb.MovieEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteViewModel( private val repository: MovieRepository):ViewModel() {

    fun insertMovieToDatabase(movieToInsert:MovieEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertMovieToDatabase(movieToInsert)
        }
    }

     // Get movies from the database
    fun getRoomMovies() = repository.getRoomMovies()

    // Get movies from the API/Retrofit call
    fun getPopularMovies(){
        repository.getPopularMovies(1)
    }
}