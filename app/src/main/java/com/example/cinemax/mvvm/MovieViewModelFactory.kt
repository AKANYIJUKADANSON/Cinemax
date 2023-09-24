package com.example.cinemax.mvvm

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cinemax.retrofit.MovieAPIService
import com.example.cinemax.retrofit.RetrofitInstance
import com.example.cinemax.roomdb.MovieDatabase

class MovieViewModelFactory(
    private val repository: MovieRepository,
    private val movieAPI: MovieAPIService
    ):ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(RemoteViewModel::class.java)){
                return RemoteViewModel(repository, movieAPI) as T
            }
            throw IllegalArgumentException("Unknown view model class")
        }
}