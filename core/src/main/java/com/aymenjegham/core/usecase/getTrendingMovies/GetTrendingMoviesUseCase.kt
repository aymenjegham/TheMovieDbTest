package com.aymenjegham.core.usecase.getTrendingMovies


import com.aymenjegham.core.domain.movie.Movie
import com.aymenjegham.core.helper.Response
import kotlinx.coroutines.flow.Flow

interface GetTrendingMoviesUseCase {

    operator fun invoke(): Flow<Response<List<Movie>>>
}