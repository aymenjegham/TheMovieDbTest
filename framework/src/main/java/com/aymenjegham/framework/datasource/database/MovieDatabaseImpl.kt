package com.aymenjegham.framework.datasource.database


import androidx.paging.PagingSource
import com.aymenjegham.framework.database.MovieDao
import com.aymenjegham.framework.entity.MovieEntity
import javax.inject.Inject

class MovieDatabaseImpl @Inject constructor(
    private val movieDao: MovieDao,
) : MovieDatabase {

    override fun getAll(): PagingSource<Int, MovieEntity> = movieDao.getAll()

    override suspend fun insertAll(movies: List<MovieEntity>) = movieDao.insertAll(movies)

    override suspend fun deleteAll() = movieDao.deleteAll()


}