package com.example.imdbclone.models.persons.cast


import com.squareup.moshi.Json

data class CastData(
    @Json(name = "cast")
    val cast: List<Cast>,
    @Json(name = "crew")
    val crew: List<Crew>,
    @Json(name = "id")
    val id: Int
)