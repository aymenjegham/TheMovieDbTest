package com.aymenjegham.framework.datasource.database.movieDetails


import com.aymenjegham.framework.entity.MovieDetailsEntity


interface MovieDetailsDatabase {


    fun getMovieDetails(movieId :String): MovieDetailsEntity

    suspend fun insertMovieDetails(movieDetailsEntity: MovieDetailsEntity)


}