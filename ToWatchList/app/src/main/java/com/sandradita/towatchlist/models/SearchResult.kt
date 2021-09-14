package com.sandradita.towatchlist.models


data class SearchResult(
    val searchType: SearchType,
    val expression: String,
    val results: List<Movie>,
    val errorMessage: String?
)


enum class SearchType {
    Title, Movie, Series, Name, Episode, Company, Keyword, All
}