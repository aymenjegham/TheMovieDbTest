package com.aymenjegham.framework.di.module


import com.aymenjegham.framework.datasource.database.MovieDatabase
import com.aymenjegham.framework.datasource.database.MovieDatabaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourcesModule {

    @Binds
    abstract fun bindsUserDatabase(movieDatabaseImpl: MovieDatabaseImpl): MovieDatabase


}