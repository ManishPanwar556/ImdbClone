package com.example.imdbclone.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.models.movies.Result
import com.example.imdbclone.repository.TmdbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TmdbViewModel : ViewModel() {
    private var _upcomingMovies = MutableLiveData<List<Result>>()

    val upComingMovies: LiveData<List<Result>>
        get() = _upcomingMovies

    private var _trendingMovies = MutableLiveData<List<Result>>()

    val trendingMovies: LiveData<List<Result>>
        get() = _trendingMovies

    private var _highRated = MutableLiveData<List<Result>>()

    val highRated: LiveData<List<Result>>
        get() = _highRated

    init {
        setUpLiveData()
    }
    private val repository=TmdbRepository()
    private fun setUpLiveData() {
        viewModelScope.launch(Dispatchers.IO) {
             val res1=repository.getUpComingMovies()
             val res2=repository.getTrendingMovies()
             val res3=repository.getTopRatedMovies()
            if(res1.isSuccessful&&res2.isSuccessful&&res3.isSuccessful){
                _upcomingMovies.postValue(res1.body()?.results)
                _trendingMovies.postValue(res2.body()?.results)
                _highRated.postValue(res3.body()?.results)
            }
            else{
                Log.e("ViewModel","${res1.isSuccessful}${res2.isSuccessful}${res3.isSuccessful}")
            }
        }
    }
}