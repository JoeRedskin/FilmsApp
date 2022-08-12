package com.example.filmsapp

import android.util.Log

class FilmRepository (private val filmService: FilmService){

    suspend fun getFilms(): List<Film>?{
        var result: List<Film>? = null
        return try {
            Log.d(TAG, "get Films")
            result = filmService.getFilms().items
            Log.d(TAG, result.toString())
            result
        } catch (t: Throwable) {
            Log.e(TAG, t.message.toString())
            result
        }
    }

    companion object {
        private const val TAG = "FilmRepository"
    }

}