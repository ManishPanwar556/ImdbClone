package com.example.imdbclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imdbclone.adapters.CastDetailsAdapter
import com.example.imdbclone.databinding.ActivityMainBinding
import com.example.imdbclone.databinding.ActivityMovieDetailBinding
import com.example.imdbclone.models.movies.IndividualMovieData
import com.example.imdbclone.models.persons.cast.CastData
import com.example.imdbclone.utils.Utilities
import com.example.imdbclone.viewModel.MovieDetailViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MovieDetailActivity : AppCompatActivity() {
    private val viewModel by lazy {
        ViewModelProvider(this).get(MovieDetailViewModel::class.java)
    }
    private lateinit var rev: MultiSnapRecyclerView
    private lateinit var chipGroup: ChipGroup
    private lateinit var poster: ImageView
    private lateinit var backPoster: ImageView
    private lateinit var rating: TextView
    private lateinit var descriptionText:TextView
    private lateinit var binding: ActivityMovieDetailBinding
    private lateinit var title:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val movieId = intent.extras?.getInt("movieId")
        rev = binding.castRecyclerView
        chipGroup = binding.chipGroup
        backPoster = binding.backPoster
        poster = binding.poster
        rating = binding.stars
        descriptionText=binding.descriptionText
        title=binding.movieTitle
        getCast(movieId)
        getMovieDetail(movieId)
        viewModel.movieDetail.observe(this, Observer {
            setUp(it)

        })
        viewModel.creditDetail.observe(this, Observer {
            setUpRecyclerView(it)
        })
    }

    private fun setUp(data: IndividualMovieData) {
        Glide.with(this).load("${Utilities.IMG_URL}${data.posterPath}").into(poster)
        Glide.with(this).load("${Utilities.IMG_URL}${data.backdropPath}").into(backPoster)
        rating.text = "${data.voteAverage}/10"
        descriptionText.text=data.overview
        title.text=data.originalTitle
        data.genres.forEach {
            val chip = Chip(this)
            chip.text = it.name
            chipGroup.addView(chip)
        }
    }

    private fun setUpRecyclerView(castData: CastData) {
        rev.adapter = CastDetailsAdapter(castData.cast)
        rev.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun getMovieDetail(movieId: Int?) {
        movieId?.let {
            viewModel.getMovieDetail(movieId)
        }
    }

    private fun getCast(movieId: Int?) {
        movieId?.let {
            viewModel.getCredits(movieId)
        }
    }
}