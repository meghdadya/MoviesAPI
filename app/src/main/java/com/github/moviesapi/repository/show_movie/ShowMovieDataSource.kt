package com.github.moviesapi.repository.show_movie

import com.github.moviesapi.network.BaseDataSource
import com.github.moviesapi.network.api.MovieApi
import com.github.moviesapi.network.api.SearchApi
import javax.inject.Inject

class ShowMovieDataSource @Inject constructor(private val api: MovieApi) : BaseDataSource() {
    suspend fun getMovie(id: Int) = getResult {
        api.fetchMovie(id = id)
    }
}