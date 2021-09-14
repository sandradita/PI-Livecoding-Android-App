package com.sandradita.towatchlist.network

import com.sandradita.towatchlist.models.MovieFullInfo
import com.sandradita.towatchlist.models.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path


interface ImdbApi {

    @GET("{lang}/API/Search/{api_key}/{expression}")
    suspend fun search(
        @Path("expression") expression: String,
        @Path("api_key") apiKey: String,
        @Path("lang") lang: String = "en"
    ): SearchResult

    @GET("{lang}/API/Title/{api_key}/{movieId}")
    suspend fun fullInfo(
        @Path("movieId") movieId: String,
        @Path("api_key") apiKey: String,
        @Path("lang") lang: String = "en"
    ): MovieFullInfo

}