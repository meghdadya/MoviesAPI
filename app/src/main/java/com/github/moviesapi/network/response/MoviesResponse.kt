package com.github.moviesapi.network.response

import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponse(
    val page: Int? = null,
    val results: List<Result>? = null,
    val total_pages: Int? = null,
    val total_results: Int? = null
)