package com.example.cinemax.retrofit

import android.content.Context
import com.example.cinemax.roomdb.MovieDAO
import com.example.cinemax.roomdb.MovieDatabase
import com.example.cinemax.utils.Constants
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

object RetrofitInstance {

    fun getAppDatabase(context:Context):MovieDatabase{
        return MovieDatabase.createDatabase(context)
    }

    fun getAppDao(movieDatabase:MovieDatabase):MovieDAO{
        return movieDatabase.getMovieDAO()
    }

    private val retrofit =  Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    // Creating the Instance of the ApiService
    val getRetrofitInstance:MovieAPIService by lazy {
        retrofit.create(MovieAPIService::class.java)
    }
}