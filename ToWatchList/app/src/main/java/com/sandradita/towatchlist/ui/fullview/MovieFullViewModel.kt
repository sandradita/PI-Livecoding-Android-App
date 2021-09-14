package com.sandradita.towatchlist.ui.fullview

import androidx.lifecycle.*
import com.sandradita.towatchlist.models.MovieFullInfo
import com.sandradita.towatchlist.models.Movie
import com.sandradita.towatchlist.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MovieFullViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: MovieRepository
) : ViewModel() {

    val movie: Movie by lazy {
        savedStateHandle.get("movie")!!
    }

    val fullInfoLiveData: LiveData<MovieFullInfo> = repository.getFullInfoLiveData(movie.id)
    val existsLiveData: LiveData<Boolean> = repository.exists(movie)

    val isSavedToDB: Boolean
        get() = existsLiveData.value ?: false

    val isCached: Boolean
        get() = fullInfoLiveData.value != null

    fun reloadInfo() {
        val movieId = movie?.id ?: return
        viewModelScope.launch {
            repository.reloadFullInfo(movieId)
        }
    }

    fun toggleSave() {
        val movie = movie ?: return
        viewModelScope.launch {
            if (isSavedToDB) {
                repository.removeFromLocal(movie)
            } else {
                repository.saveToLocal(movie)
            }
        }
    }

}