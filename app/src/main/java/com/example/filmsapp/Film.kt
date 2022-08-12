package com.example.filmsapp

import com.google.gson.annotations.Expose

data class FilmList(
    @Expose val items: List<Film>,
)

data class Film(
    @Expose val title: String = "",
    @Expose val directorName: String = "",
    @Expose val releaseYear: Int = 0,
    @Expose val actors: List<Actor>,
)

data class Actor(
    @Expose val actorName: String,
)