package com.example.imdbclone.repository

import com.example.imdbclone.models.movies.IndividualMovieData
import com.example.imdbclone.models.movies.MovieData
import com.example.imdbclone.models.movies.UpComingMovies
import com.example.imdbclone.models.persons.cast.CastData
import com.example.imdbclone.retrofit.TmdbClient
import retrofit2.Response

class TmdbRepository {

    suspend fun getTopRatedMovies(): Response<MovieData> {
        return TmdbClient.service.getTopRatedMovies()
    }
    suspend fun getTrendingMovies(): Response<MovieData> {
        return TmdbClient.service.getTrendingMovies()
    }
    suspend fun getUpComingMovies(): Response<UpComingMovies> {
        return TmdbClient.service.getUpComingMovies()
    }

    suspend fun getMovieDetails(movieId:Int):Response<IndividualMovieData>{
        return TmdbClient.service.getMovieDetail(movieId)
    }

    suspend fun getCredits(movieId: Int):Response<CastData>{
        return TmdbClient.service.getCredits(movieId)
    }
}