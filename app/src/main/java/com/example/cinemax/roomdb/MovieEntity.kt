package com.example.cinemax.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val poster_Path: String,
    @SerializedName("backdrop_path") val backdrop_Path: String,
    @SerializedName("vote_average") val rating: Float,
    @SerializedName("release_date") val release_Date: String
)
