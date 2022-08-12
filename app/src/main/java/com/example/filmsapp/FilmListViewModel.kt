package com.example.filmsapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FilmListViewModel : ViewModel() {

    private val repository = FilmRepository(FilmRetrofitClient.retrofit)
    var filmsList = MutableLiveData<List<Film>?>()

    fun getFilms() {
        viewModelScope.launch {
            val result = repository.getFilms()
            filmsList.value = result
        }
    }
}
