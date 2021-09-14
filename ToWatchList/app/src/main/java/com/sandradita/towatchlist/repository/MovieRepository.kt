package com.sandradita.towatchlist.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sandradita.towatchlist.BuildConfig
import com.sandradita.towatchlist.database.dao.MovieDao
import com.sandradita.towatchlist.database.dao.MovieFullInfoDao
import com.sandradita.towatchlist.models.MovieFullInfo
import com.sandradita.towatchlist.models.Movie
import com.sandradita.towatchlist.models.SearchResult
import com.sandradita.towatchlist.network.ImdbApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.util.*


class MovieRepository(
    private val imdbApi: ImdbApi,
    private val movieDao: MovieDao,
    private val movieFullInfoDao: MovieFullInfoDao
) {

    fun getSavedMovies() = movieDao.getAll()

    suspend fun search(expression: String): SearchResult {
        return imdbApi.search(
            expression,
            BuildConfig.IMDB_API_KEY,
            Locale.getDefault().language
        )
    }

    fun getFullInfoLiveData(movieId: String): LiveData<MovieFullInfo> {
        return movieFullInfoDao.getCachedMovie(movieId)
    }

    suspend fun reloadFullInfo(movieId: String) {
        val fullInfo = imdbApi.fullInfo(
            movieId,
            BuildConfig.IMDB_API_KEY,
            Locale.getDefault().language
        )
        movieFullInfoDao.save(fullInfo)
    }

    suspend fun saveToLocal(movie: Movie) {
        return movieDao.save(movie)
    }

    suspend fun removeFromLocal(movie: Movie) {
        return movieDao.remove(movie)
    }

    fun exists(movie: Movie?): LiveData<Boolean> {
        if (movie == null) return MutableLiveData(false)
        return movieDao.exists(movie.id)
    }

}