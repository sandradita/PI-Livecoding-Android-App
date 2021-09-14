package com.postindustria.livecodingapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.postindustria.livecodingapp.model.Movie


@Dao
interface MovieDao {

    @Query("SELECT EXISTS(SELECT * FROM Movie WHERE id = :movieId)")
    fun isExists(movieId: String): LiveData<Boolean>

    @Query("SELECT * FROM Movie")
    fun getAll(): LiveData<List<Movie>>

    @Insert
    suspend fun save(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)

}