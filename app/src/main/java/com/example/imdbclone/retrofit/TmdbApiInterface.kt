package com.example.imdbclone.retrofit

import com.example.imdbclone.models.movies.IndividualMovieData
import com.example.imdbclone.models.movies.MovieData
import com.example.imdbclone.models.movies.UpComingMovies
import com.example.imdbclone.models.persons.artist.ArtistData
import com.example.imdbclone.models.persons.cast.CastData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApiInterface {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: String
    ): Response<MovieData>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(@Path("movie_id") movieId: Int): Response<CastData>

    @GET("person/{person_id}")
    suspend fun getArtistDetails(@Path("person_id") personId: Int): Response<ArtistData>

    @GET("trending/movie/week")
    suspend fun getTrendingMovies():Response<MovieData>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies():Response<MovieData>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies():Response<UpComingMovies>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId:Int):Response<IndividualMovieData>

}