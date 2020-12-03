package com.github.moviesapi.repository

import androidx.paging.PagingSource
import com.github.moviesapi.network.response.DiscoverResponse
import com.github.moviesapi.network.response.Result

class MoviesListPagingDataSource(
    private val repository: MoviesListRepository
) : PagingSource<Int, Result>() {

    private lateinit var movieListResponse: DiscoverResponse

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        return try {
            val nextPage = params.key ?: 1
            repository.fetchMovies(nextPage) { result ->
                result.data?.let { data ->
                    movieListResponse = data
                }
            }
            LoadResult.Page(
                data = movieListResponse.results,
                prevKey = if (nextPage == 1) null else nextPage - 1,
                nextKey = if (nextPage < movieListResponse.total_pages)
                    movieListResponse.page.plus(1) else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}