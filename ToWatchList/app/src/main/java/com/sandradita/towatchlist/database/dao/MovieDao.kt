package com.sandradita.towatchlist.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sandradita.towatchlist.models.Movie


@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getAll(): LiveData<List<Movie>>

    @Insert(onConflict = REPLACE)
    suspend fun save(movie: Movie)

    @Delete
    suspend fun remove(movie: Movie)

    @Query("SELECT EXISTS(SELECT * FROM Movie WHERE id = :movieId)")
    fun exists(movieId : String) : LiveData<Boolean>

}