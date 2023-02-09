package com.aymenjegham.core.usecase.getTrendingMovies

import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.core.domain.movie.Movie
import com.aymenjegham.core.helper.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTrendingMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetTrendingMoviesUseCase {

    override fun invoke(): Flow<Response<List<Movie>>> = movieRepository.getTrendingMovies()

}