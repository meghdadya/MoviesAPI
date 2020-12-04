package com.github.moviesapi.repository.show_movie

import com.github.moviesapi.network.resultLiveData

import javax.inject.Inject

class ShowMovieRepository @Inject constructor(private val dataSource: ShowMovieDataSource) {

    fun getMovie(id: Int) = resultLiveData(networkCall = { dataSource.getMovie(id) })

}