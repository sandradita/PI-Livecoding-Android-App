package com.postindustria.livecodingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.postindustria.livecodingapp.model.Movie


@Database(
    entities = [Movie::class],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        fun create(context: Context): LocalDatabase {
            return Room.databaseBuilder(context, LocalDatabase::class.java, "movies.db")
                .build()
        }
    }

}