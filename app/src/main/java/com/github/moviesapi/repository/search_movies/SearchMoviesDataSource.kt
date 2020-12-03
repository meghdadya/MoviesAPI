package com.github.moviesapi.repository.search_movies

import com.github.moviesapi.network.BaseDataSource
import com.github.moviesapi.network.api.SearchApi
import javax.inject.Inject

class SearchMoviesDataSource @Inject constructor(private val api: SearchApi) : BaseDataSource() {
    suspend fun searchMovies(page: Int, query: String) = getResult {
        api.search(page = page, query = query)
    }
}