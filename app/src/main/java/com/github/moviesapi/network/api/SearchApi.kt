package com.github.moviesapi.network.api

import com.github.moviesapi.BuildConfig
import com.github.moviesapi.network.response.MoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {

    @GET("search/movie")
    suspend fun search(
        @Query("api_key") api_key: String = BuildConfig.SAMPLE_TOKEN,
        @Query("page") page: Int,
        @Query("query") query: String
    ): Response<MoviesResponse>


}