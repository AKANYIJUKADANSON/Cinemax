package com.example.cinemax.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.cinemax.R
import com.example.cinemax.databinding.CustomMovieBinding
import com.example.cinemax.models.Movie
import com.example.cinemax.roomdb.MovieEntity
import com.example.cinemax.utils.GlideLoader

class MovieAdapter(private val context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val itemBinding: CustomMovieBinding):
        RecyclerView.ViewHolder(itemBinding.root){
            fun bind(movie:MovieEntity){
                itemBinding.movieTitle.text = movie.title
                itemBinding.movieRating.text = movie.popularity.toString()
                itemBinding.movieReleaseDate.text = movie.release_date

                val posterURL = "https://image.tmdb.org/t/p/w342/"+movie.poster_path

//                Glide.with(itemView).load(posterURL).into(itemBinding.imageViewMovie)
                itemBinding.imageViewMovie.load(posterURL){
                    crossfade(true)
                    placeholder(R.drawable.placeholder)
                    scale(Scale.FILL)
                }

            }
        }


    private val differCallback = object : DiffUtil.ItemCallback<MovieEntity>(){
        override fun areItemsTheSame(oldItem:MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            CustomMovieBinding.inflate(
                LayoutInflater.from(parent.context),parent,false
            )
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val currentMovie = differ.currentList[position]
        holder.bind(currentMovie)

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

}