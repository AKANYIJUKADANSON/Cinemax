package com.example.cinemax.retrofit

import android.database.Observable
import com.example.cinemax.models.MovieResponse
import com.example.cinemax.models.MyMovieListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieAPIService {
    @GET("movie/popular")
    fun getPopularMovies( @Query("api_key") apiKey: String, @Query("page") page: Int ): Call<MyMovieListResponse>

    // Searching for a movie
    @GET("search/movie")
    fun searchMovie(@Query("query") query: String, @Query("page") page: Int): Observable<MovieResponse>
}