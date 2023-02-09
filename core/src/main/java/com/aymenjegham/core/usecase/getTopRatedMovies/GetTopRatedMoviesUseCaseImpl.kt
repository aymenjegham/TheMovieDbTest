package com.aymenjegham.core.usecase.getTopRatedMovies

import androidx.paging.PagingData
import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.core.domain.movie.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetTopRatedMoviesUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetTopRatedMoviesUseCase {

    override fun invoke(): Flow<PagingData<Movie>> = movieRepository.getTopRatedMovies()

}