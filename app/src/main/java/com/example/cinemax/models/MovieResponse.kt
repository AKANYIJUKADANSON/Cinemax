package com.example.cinemax.models

import com.example.cinemax.roomdb.MovieEntity
import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieEntity>,
    @SerializedName("total_pages") val pages: Int
)
