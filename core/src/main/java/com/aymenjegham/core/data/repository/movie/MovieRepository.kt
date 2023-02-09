package com.aymenjegham.core.data.repository.movie

import androidx.paging.PagingData
import com.aymenjegham.core.domain.movie.Movie
import com.aymenjegham.core.domain.movieDetails.MovieDetails
import com.aymenjegham.core.helper.Response
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getTopRatedMovies(): Flow<PagingData<Movie>>

    fun getTrendingMovies(): Flow<Response<List<Movie>>>

    fun getMovieDetails(movieId :String): Flow<Response<MovieDetails>>

}