package com.postindustria.livecodingapp.network

import com.postindustria.livecodingapp.model.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path


interface ImbdApi {

    @GET("API/Search/{api_key}/{query}")
    suspend fun search(
        @Path("api_key") apiKey: String,
        @Path("query") query: String
    ): SearchResult

}