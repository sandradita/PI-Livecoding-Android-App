package com.postindustria.livecodingapp.ui.savedmovies

import androidx.lifecycle.ViewModel
import com.postindustria.livecodingapp.repo.MovieRepository


class SaveMovieViewModel : ViewModel() {

    val saveMovies = MovieRepository.getAll()

}