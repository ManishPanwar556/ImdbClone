package com.example.imdbclone.models.movies


import com.squareup.moshi.Json

data class Genre(
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: String
)