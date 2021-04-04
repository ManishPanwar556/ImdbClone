package com.example.imdbclone.models.movies


import com.squareup.moshi.Json

data class BelongsToCollection(
    @Json(name = "backdrop_path")
    val backdropPath: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String,
    @Json(name = "poster_path")
    val posterPath: String
)