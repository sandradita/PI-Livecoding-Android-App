package com.sandradita.towatchlist.di

import com.sandradita.towatchlist.database.dao.MovieDao
import com.sandradita.towatchlist.database.dao.MovieFullInfoDao
import com.sandradita.towatchlist.network.ImdbApi
import com.sandradita.towatchlist.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(
        imdbApi: ImdbApi,
        movieDao: MovieDao,
        movieFullInfoDao: MovieFullInfoDao
    ): MovieRepository {
        return MovieRepository(imdbApi, movieDao, movieFullInfoDao)
    }

}