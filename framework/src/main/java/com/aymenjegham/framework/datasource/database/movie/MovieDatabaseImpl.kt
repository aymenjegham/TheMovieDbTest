package com.aymenjegham.framework.datasource.database.movie


import androidx.paging.PagingSource
import com.aymenjegham.framework.database.MovieDao
import com.aymenjegham.framework.entity.MovieEntity
import javax.inject.Inject

class MovieDatabaseImpl @Inject constructor(
    private val movieDao: MovieDao,
) : MovieDatabase {

    override fun getTopRated(): PagingSource<Int, MovieEntity> = movieDao.getTopRated()
    override fun getTrendy(): List<MovieEntity> = movieDao.getTrendy()

    override suspend fun insertAll(movies: List<MovieEntity>) = movieDao.insertAll(movies)

    override suspend fun deleteAll() = movieDao.deleteAllTopRated()
    override suspend fun deleteAllTrendy() = movieDao.deleteAllTrendy()


}