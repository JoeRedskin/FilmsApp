package com.example.filmsapp

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FilmRetrofitClient {
    val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
    val retrofit: FilmService = Retrofit.Builder()
        .baseUrl("https://raw.githubusercontent.com/constanta-android-dev/intership-wellcome-task/main/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(FilmService::class.java)
}