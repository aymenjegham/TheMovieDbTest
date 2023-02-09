package com.aymenjegham.framework.datasource.database.movieDetails


import com.aymenjegham.framework.database.MovieDetailsDao
import com.aymenjegham.framework.entity.MovieDetailsEntity
import javax.inject.Inject

class MovieDetailsDatabaseImpl @Inject constructor(
    private val movieDetailsDao: MovieDetailsDao,
) : MovieDetailsDatabase {


    override fun getMovieDetails(movieId: String): MovieDetailsEntity =
        movieDetailsDao.getMovieDetails(movieId)

    override suspend fun insertMovieDetails(movieDetailsEntity: MovieDetailsEntity) =
        movieDetailsDao.insert(movieDetailsEntity)


}