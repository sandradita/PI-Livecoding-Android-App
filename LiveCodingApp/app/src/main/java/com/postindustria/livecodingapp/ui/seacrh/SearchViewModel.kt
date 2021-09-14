package com.postindustria.livecodingapp.ui.seacrh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.postindustria.livecodingapp.model.Movie
import com.postindustria.livecodingapp.repo.MovieRepository
import kotlinx.coroutines.launch


class SearchViewModel: ViewModel() {

    private val _resultsLiveData = MutableLiveData<List<Movie>>()
    val resultsLiveData: LiveData<List<Movie>> = _resultsLiveData

    fun search(query: String) {
        viewModelScope.launch {
            val result = MovieRepository.search(query)
            _resultsLiveData.postValue(result.results)
        }
    }

}