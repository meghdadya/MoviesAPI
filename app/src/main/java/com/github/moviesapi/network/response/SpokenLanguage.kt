package com.github.moviesapi.network.response

import kotlinx.serialization.Serializable

@Serializable
data class SpokenLanguage(
    val english_name: String? =null,
    val iso_639_1: String? =null,
    val name: String? =null
)