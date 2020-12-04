package com.github.moviesapi.network.response

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int? =null,
    val name: String? =null
)