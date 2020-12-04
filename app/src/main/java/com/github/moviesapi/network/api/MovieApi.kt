package com.github.moviesapi.network.api

import com.github.moviesapi.BuildConfig
import com.github.moviesapi.network.Result
import com.github.moviesapi.network.response.MovieResponse
import com.github.moviesapi.network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{movie_id}")
    suspend fun fetchMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") api_key: String = BuildConfig.SAMPLE_TOKEN
    ): Response<MovieResponse>


}