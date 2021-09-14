package com.sandradita.towatchlist.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.sandradita.towatchlist.models.MovieFullInfo


@Dao
interface MovieFullInfoDao {

    @Insert(onConflict = REPLACE)
    suspend fun save(movieFullInfo: MovieFullInfo)

    @Query("SELECT * FROM MovieFullInfo WHERE id = :movieId")
    fun getCachedMovie(movieId: String): LiveData<MovieFullInfo>

    @Query("SELECT EXISTS(SELECT * FROM Movie WHERE id = :movieId)")
    fun exists(movieId : String) : LiveData<Boolean>

}