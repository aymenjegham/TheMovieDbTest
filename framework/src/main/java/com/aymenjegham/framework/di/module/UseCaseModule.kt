package com.aymenjegham.framework.di.module

import com.aymenjegham.core.usecase.getMovieDetails.GetMovieDetailsUseCase
import com.aymenjegham.core.usecase.getMovieDetails.GetMovieDetailsUseCaseImpl
import com.aymenjegham.core.usecase.getTopRatedMovies.GetTopRatedMoviesUseCase
import com.aymenjegham.core.usecase.getTopRatedMovies.GetTopRatedMoviesUseCaseImpl
import com.aymenjegham.core.usecase.getTrendingMovies.GetTrendingMoviesUseCase
import com.aymenjegham.core.usecase.getTrendingMovies.GetTrendingMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Singleton
    @Binds
    abstract fun bindGetTopRatedMovies(getTopRatedMoviesUseCaseImpl: GetTopRatedMoviesUseCaseImpl): GetTopRatedMoviesUseCase

    @Singleton
    @Binds
    abstract fun bindGetTrendyMovies(getTrendingMoviesUseCaseImpl: GetTrendingMoviesUseCaseImpl): GetTrendingMoviesUseCase

    @Singleton
    @Binds
    abstract fun bindGetMovieDetails(getMovieDetailsUseCaseImpl: GetMovieDetailsUseCaseImpl): GetMovieDetailsUseCase

}