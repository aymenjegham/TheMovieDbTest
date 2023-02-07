package com.aymenjegham.framework.di.module


import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.framework.data.repositoryImpl.MovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindsMovieRepository(movieRepository: MovieRepositoryImpl) : MovieRepository

}