package com.aymenjegham.core.usecase.getAllMovies

import androidx.paging.PagingData
import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.core.domain.movie.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetAllMoviesUseCaseImpl @Inject constructor(private val movieRepository: MovieRepository) :
    GetAllMoviesUseCase {

    override fun invoke(): Flow<PagingData<Movie>> = movieRepository.getMovies()

}