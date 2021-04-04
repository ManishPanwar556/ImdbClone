package com.example.imdbclone.models.persons.artist


import com.squareup.moshi.Json

data class ArtistData(
    @Json(name = "adult")
    val adult: Boolean,
    @Json(name = "also_known_as")
    val alsoKnownAs: List<String>,
    @Json(name = "biography")
    val biography: String,
    @Json(name = "birthday")
    val birthday: String,
    @Json(name = "deathday")
    val deathday: Any,
    @Json(name = "gender")
    val gender: Int,
    @Json(name = "homepage")
    val homepage: Any,
    @Json(name = "id")
    val id: Int,
    @Json(name = "imdb_id")
    val imdbId: String,
    @Json(name = "known_for_department")
    val knownForDepartment: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "place_of_birth")
    val placeOfBirth: String,
    @Json(name = "popularity")
    val popularity: Double,
    @Json(name = "profile_path")
    val profilePath: String
)