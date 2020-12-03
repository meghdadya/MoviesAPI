package com.github.moviesapi.repository.search_movies

import com.github.moviesapi.network.Result
import com.github.moviesapi.network.response.MoviesResponse
import timber.log.Timber

import javax.inject.Inject

class SearchMoviesRepository @Inject constructor(private val dataSource: SearchMoviesDataSource) {
    suspend fun searchMovies(page: Int, query: String, callback: (Result<MoviesResponse>) -> Unit) {
        val response = dataSource.searchMovies(page, query)
        if (response.status == Result.Status.SUCCESS) {
            callback(response)
        } else if (response.status == Result.Status.ERROR) {
            Timber.d(response.message)
        }
    }
}