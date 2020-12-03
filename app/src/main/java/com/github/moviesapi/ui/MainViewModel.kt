package com.github.moviesapi.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.github.moviesapi.network.response.Result
import com.github.moviesapi.repository.movies_list.MoviesListPagingDataSource
import com.github.moviesapi.repository.movies_list.MoviesListRepository
import com.github.moviesapi.repository.search_movies.SearchMoviesPagingDataSource
import com.github.moviesapi.repository.search_movies.SearchMoviesRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel @ViewModelInject constructor(
    private val moviesListRepository: MoviesListRepository,
    private val searchMoviesRepository: SearchMoviesRepository
) :
    ViewModel() {


    fun observeDiscoverMovies(): Flow<PagingData<Result>> {
        return Pager(PagingConfig(pageSize = 6)) {
            MoviesListPagingDataSource(moviesListRepository)
        }.flow.cachedIn(viewModelScope)
    }

    fun observeSearchMovies(query: String): Flow<PagingData<Result>> {
        return Pager(PagingConfig(pageSize = 6)) {
            SearchMoviesPagingDataSource(searchMoviesRepository, query)
        }.flow.cachedIn(viewModelScope)
    }

}