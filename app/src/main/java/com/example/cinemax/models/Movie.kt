package com.example.cinemax.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_Path: String,
    @SerializedName("backdrop_path") val backdrop_Path: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val release_Date: String
)
