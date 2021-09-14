package com.postindustria.livecodingapp.repo


import android.content.Context
import com.postindustria.livecodingapp.database.LocalDatabase
import com.postindustria.livecodingapp.database.MovieDao
import com.postindustria.livecodingapp.model.Movie
import com.postindustria.livecodingapp.network.ImbdApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRepository {

    private const val API_KEY = "k_uvx4fu0m"

    private val imdbApi: ImbdApi
    private lateinit var movieDao: MovieDao

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://imdb-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        imdbApi = retrofit.create(ImbdApi::class.java)
    }

    fun init(context: Context) {
        val database = LocalDatabase.create(context)
        movieDao = database.movieDao()
    }

    suspend fun search(query: String) = imdbApi.search(API_KEY, query)

    fun isExists(movieId: String) = movieDao.isExists(movieId)

    fun getAll() = movieDao.getAll()

    suspend fun save(movie: Movie) = movieDao.save(movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie)

}