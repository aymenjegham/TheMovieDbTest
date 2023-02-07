package com.aymenjegham.framework.di.module


import com.aymenjegham.framework.database.Database
import com.aymenjegham.framework.database.MovieDao
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
    fun provideProductDao(database: Database): MovieDao = database.movieDao()

    @Provides
    @Singleton
    fun provideRemoteKeysDao(database: Database): RemoteKeysDao = database.remoteKeysDao()

}