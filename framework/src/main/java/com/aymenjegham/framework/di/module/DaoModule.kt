package com.aymenjegham.framework.di.module


import com.aymenjegham.framework.database.Database
import com.aymenjegham.framework.database.MovieDao
import com.aymenjegham.framework.database.MovieDetailsDao
import com.aymenjegham.framework.database.RemoteKeysDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DaoModule {


    @Provides
    @Singleton
    fun provideMovieDao(database: Database): MovieDao = database.movieDao()

    @Provides
    @Singleton
    fun provideMovieDetailsDao(database: Database): MovieDetailsDao = database.movieDetailsDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(database: Database): RemoteKeysDao = database.remoteKeysDao()

}