package com.aymenjegham.framework.di.module

import com.aymenjegham.core.usecase.getAllMovies.GetAllMoviesUseCase
import com.aymenjegham.core.usecase.getAllMovies.GetAllMoviesUseCaseImpl
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
    abstract fun bind(getAllMoviesUseCaseImpl: GetAllMoviesUseCaseImpl): GetAllMoviesUseCase

}