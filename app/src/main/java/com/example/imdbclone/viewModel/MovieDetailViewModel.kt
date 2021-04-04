package com.example.imdbclone.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdbclone.models.movies.IndividualMovieData
import com.example.imdbclone.models.persons.cast.CastData
import com.example.imdbclone.repository.TmdbRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel : ViewModel() {
    private val _movieDetail = MutableLiveData<IndividualMovieData>()

    val movieDetail: LiveData<IndividualMovieData>
        get() = _movieDetail

    private val _creditDetail = MutableLiveData<CastData>()

    val creditDetail: LiveData<CastData>
        get() = _creditDetail

    private val repository = TmdbRepository()
    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.getMovieDetails(movieId)
            if (res.isSuccessful) {
                _movieDetail.postValue(res.body())
            }
        }
    }

    fun getCredits(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val res = repository.getCredits(movieId)
            if (res.isSuccessful) {
               _creditDetail.postValue(res.body())
            }
        }
    }
}