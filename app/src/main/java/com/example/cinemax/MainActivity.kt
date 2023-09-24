package com.example.cinemax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.cinemax.adapter.MovieAdapter
import com.example.cinemax.databinding.ActivityMainBinding
import com.example.cinemax.models.Movie
import com.example.cinemax.mvvm.*
import com.example.cinemax.retrofit.MovieAPIService
import com.example.cinemax.retrofit.RetrofitInstance
import com.example.cinemax.roomdb.MovieDAO
import com.example.cinemax.roomdb.MovieDatabase
import com.example.cinemax.roomdb.MovieEntity
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var movieViewModel: RemoteViewModel
    private val movieAdapter by lazy{MovieAdapter(this)}

    private val movieAPI:MovieAPIService by lazy {
        RetrofitInstance.getRetrofitInstance
    }

    private val movieDatabase:MovieDatabase by lazy {
        RetrofitInstance.getAppDatabase(this)
    }

    private val movieDao:MovieDAO by lazy {
        RetrofitInstance.getAppDao(movieDatabase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val movieRepository = MovieRepository(movieDatabase)
        val factory = MovieViewModelFactory(movieRepository, movieAPI)
        movieViewModel =  ViewModelProvider(this, factory)[RemoteViewModel::class.java]

        initMovieViewModel()

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        lifecycleScope.launch {
            binding.movieRecyclerView.apply {
                visibility = View.VISIBLE
                layoutManager = StaggeredGridLayoutManager(
                    2,
                    StaggeredGridLayoutManager.VERTICAL
                )
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }

        this.let {
            lifecycleScope.launch {
                movieViewModel.getRoomMovies().observe(this@MainActivity,
                    {movies ->
                        movieAdapter.differ.submitList(movies)
                        Log.e("Room movies", "$movies")
                    })
            }
        }
    }

    private fun initMovieViewModel(){
        movieViewModel.getPopularMovies(1)
    }


}
