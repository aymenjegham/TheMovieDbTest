package com.aymenjegham.framework.datasource.database


import androidx.paging.PagingSource
import com.aymenjegham.framework.entity.MovieEntity


interface MovieDatabase {

    fun getAll(): PagingSource<Int, MovieEntity>

    suspend fun insertAll(movies: List<MovieEntity>)

    suspend fun deleteAll()

}