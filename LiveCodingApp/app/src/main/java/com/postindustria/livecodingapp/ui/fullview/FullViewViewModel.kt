package com.postindustria.livecodingapp.ui.fullview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.postindustria.livecodingapp.model.Movie
import com.postindustria.livecodingapp.repo.MovieRepository
import kotlinx.coroutines.launch


class FullViewViewModel:ViewModel() {

    fun isExists(movieId: String) = MovieRepository.isExists(movieId)

    fun save(movie: Movie) {
        viewModelScope.launch {
            MovieRepository.save(movie)
        }
    }

    fun delete(movie: Movie) {
        viewModelScope.launch {
            MovieRepository.delete(movie)
        }
    }

}