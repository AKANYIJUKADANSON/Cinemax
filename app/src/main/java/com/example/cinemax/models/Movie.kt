package com.example.cinemax.models

import com.google.gson.annotations.SerializedName


data class Movie(
    val id: Long ,
    val title: String,
    val overview: String,
//    val poster_Path: String,
//    val backdrop_Path: String,
//    val rating: Float,
    val release_Date: String
)


