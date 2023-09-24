package com.example.cinemax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        // Getting movie details
        val movieId = intent.getStringExtra("id")
        val movieTitle = intent.getStringExtra("title")
        val releaseDate = intent.getStringExtra("release_date")
        val language = intent.getStringExtra("language")
        val overview = intent.getStringExtra("overview")
        val votes = intent.getStringExtra("votes")
        val popularity = intent.getStringExtra("popularity")

        val tv_overview = findViewById<TextView>(R.id.overview)
        tv_overview.text = overview
    }
}