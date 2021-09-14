package com.sandradita.towatchlist.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sandradita.towatchlist.models.SearchResult
import com.sandradita.towatchlist.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _resultLiveData = MutableLiveData<SearchResult>()
    val resultLiveData: LiveData<SearchResult> = _resultLiveData

    fun search(query: String) {
        viewModelScope.launch {
            val result = repository.search(query)
            _resultLiveData.postValue(result)
        }
    }

}