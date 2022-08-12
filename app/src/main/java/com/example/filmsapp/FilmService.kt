package com.example.filmsapp

import retrofit2.http.GET

interface FilmService {
    @GET("films.json")
    suspend fun getFilms(): FilmList
}