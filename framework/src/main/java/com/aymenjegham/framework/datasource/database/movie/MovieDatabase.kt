package com.aymenjegham.framework.datasource.database.movie


import androidx.paging.PagingSource
import com.aymenjegham.framework.entity.MovieEntity


interface MovieDatabase {

    fun getTopRated(): PagingSource<Int, MovieEntity>

    fun getTrendy(): List<MovieEntity>

    suspend fun insertAll(movies: List<MovieEntity>)

    suspend fun deleteAll()

    suspend fun deleteAllTrendy()

}