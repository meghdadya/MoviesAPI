package com.github.moviesapi.di

import com.github.moviesapi.BuildConfig
import com.github.moviesapi.network.api.DiscoverApi
import com.github.moviesapi.network.api.SearchApi
import com.github.moviesapi.repository.movies_list.MoviesListDataSource
import com.github.moviesapi.repository.movies_list.MoviesListRepository
import com.github.moviesapi.repository.search_movies.SearchMoviesDataSource
import com.github.moviesapi.repository.search_movies.SearchMoviesRepository
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HiltModules {

    @Provides
    @Singleton
    @UnstableDefault
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .retryOnConnectionFailure(true).addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun provideAPI(retrofit: Retrofit): DiscoverApi =
        retrofit.create(DiscoverApi::class.java)

    @Provides
    fun provideMoviesListDataSource(api: DiscoverApi) =
        MoviesListDataSource(api)

    @Singleton
    @Provides
    fun provideMoviesRepository(dataSource: MoviesListDataSource) =
        MoviesListRepository(dataSource)

    @Provides
    fun provideSearchApi(retrofit: Retrofit): SearchApi =
        retrofit.create(SearchApi::class.java)

    @Provides
    fun provideSearchMoviesDataSource(api: SearchApi) =
        SearchMoviesDataSource(api)

    @Singleton
    @Provides
    fun provideSearchMoviesRepository(dataSource: SearchMoviesDataSource) =
        SearchMoviesRepository(dataSource)

}