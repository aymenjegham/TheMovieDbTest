package com.aymenjegham.framework.di.module


import com.aymenjegham.framework.datasource.database.movie.MovieDatabase
import com.aymenjegham.framework.datasource.database.movie.MovieDatabaseImpl
import com.aymenjegham.framework.datasource.database.movieDetails.MovieDetailsDatabase
import com.aymenjegham.framework.datasource.database.movieDetails.MovieDetailsDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindMovieDatabase(movieDatabaseImpl: MovieDatabaseImpl): MovieDatabase

    @Binds
    abstract fun bindMovieDetailsDatabase(movieDetailsDatabaseImpl: MovieDetailsDatabaseImpl): MovieDetailsDatabase

}