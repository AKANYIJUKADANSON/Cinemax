package com.example.cinemax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.cinemax.databinding.ActivityMovieDetailsBinding

class MovieDetails : AppCompatActivity() {
    lateinit var binding:ActivityMovieDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details)

        // Toolbar
        setUpActionBar()

        // Getting movie details
        val posterPath = intent.getStringExtra("thumbnail")
        val movieTitle = intent.getStringExtra("title")
        val releaseDate = intent.getStringExtra("release_date")
        val language = intent.getStringExtra("language")
        val overview = intent.getStringExtra("overview")
        val votes = intent.getStringExtra("votes")
        val popularity = intent.getStringExtra("popularity")

        val posterURL = "https://image.tmdb.org/t/p/w342/$posterPath"

        // Setting the text in the toolbar to the movie title
        binding.tvToolbar.text = movieTitle

        // Setting the movie details
        binding.tvMovieTitle.text = movieTitle
        binding.tvMovieLanguage.text = language
        binding.tvMovieReleaseDate.text = releaseDate
        binding.tvMovieOverview.text = overview
        binding.tvMoviePopularity.text = popularity
        binding.tvMovieVotes.text = votes

        Glide.with(this).load(posterURL).into(binding.movieThumbnail)

    }

    private fun setUpActionBar(){
        val myToolBar = binding.detailsToolbar
        setSupportActionBar(myToolBar)
        val actionBar = supportActionBar
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
        }

        myToolBar.setNavigationOnClickListener { onBackPressed() }
    }
}