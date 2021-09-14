package com.sandradita.towatchlist.di

import android.content.Context
import com.sandradita.towatchlist.database.MovieDatabase
import com.sandradita.towatchlist.database.dao.MovieDao
import com.sandradita.towatchlist.database.dao.MovieFullInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MovieDatabase {
        return MovieDatabase.build(context)
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }

    @Singleton
    @Provides
    fun provideMovieFullInfoDao(database: MovieDatabase): MovieFullInfoDao {
        return database.movieFullInfoDao()
    }

}