
package com.github.moviesapi.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.github.moviesapi.network.response.Result
import com.github.moviesapi.repository.movies_list.MoviesListPagingDataSource
import com.github.moviesapi.repository.movies_list.MoviesListRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel @ViewModelInject constructor(
    private val moviesListRepository: MoviesListRepository
) :
    ViewModel() {



     fun observeDiscoverMovies(): Flow<PagingData<Result>> {
        return Pager(PagingConfig(pageSize = 6)) {
            MoviesListPagingDataSource(moviesListRepository)
        }.flow.cachedIn(viewModelScope)
    }


}