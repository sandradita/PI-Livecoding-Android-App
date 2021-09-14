package com.sandradita.towatchlist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sandradita.towatchlist.database.dao.MovieDao
import com.sandradita.towatchlist.database.dao.MovieFullInfoDao
import com.sandradita.towatchlist.models.Movie
import com.sandradita.towatchlist.models.MovieFullInfo


@Database(entities = [Movie::class, MovieFullInfo::class], version = 2)
abstract class MovieDatabase : RoomDatabase() {

    companion object {
        fun build(context: Context): MovieDatabase {
            return Room.databaseBuilder(context, MovieDatabase::class.java, "movies-app.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun movieDao(): MovieDao

    abstract fun movieFullInfoDao(): MovieFullInfoDao

}