package com.example.imdbclone

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Explode
import android.view.View
import android.view.Window
import android.view.animation.AnimationUtils
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbclone.adapters.MoviesAdapter
import com.example.imdbclone.databinding.ActivityMainBinding
import com.example.imdbclone.viewModel.TmdbViewModel

class MainActivity : AppCompatActivity(),ClickInterface {
    private val viewModel by lazy {
        ViewModelProvider(this).get(TmdbViewModel::class.java)
    }
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setUpAnimation()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val highestRatedRev = binding.highestRatedRev
        val trendingRev = binding.trendingRev
        val upComingRev = binding.upcomingRev

        viewModel.highRated.observe(this, Observer {
            binding.progressBar.visibility=View.GONE
            binding.movieLayout.visibility= View.VISIBLE
            highestRatedRev.adapter = MoviesAdapter(it,this)
            highestRatedRev.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        })
        viewModel.trendingMovies.observe(this, Observer {
            trendingRev.adapter = MoviesAdapter(it,this)
            trendingRev.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        })
        viewModel.upComingMovies.observe(this, Observer {
            upComingRev.adapter = MoviesAdapter(it,this)
            upComingRev.layoutManager =
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        })

    }
private fun setUpAnimation(){
    with(window){
        requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS)
        enterTransition=Explode()
        exitTransition=Explode()
    }
}
    override fun onClick(movieId: Int) {

        val intent= Intent(this,MovieDetailActivity::class.java)
        intent.putExtra("movieId",movieId)
        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle())

    }
}