package com.github.moviesapi.repository.movies_list

import com.github.moviesapi.network.api.DiscoverApi
import com.github.moviesapi.network.BaseDataSource
import javax.inject.Inject

class MoviesListDataSource @Inject constructor(private val api: DiscoverApi) : BaseDataSource() {

    suspend fun discoverMovies(page: Int) = getResult {
        api.discover(page = page)
    }

}