package com.aymenjegham.core.usecase.getMovieDetails

import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.core.domain.movieDetails.MovieDetails
import com.aymenjegham.core.helper.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMovieDetailsUseCaseImpl @Inject constructor(
    private val movieRepository: MovieRepository,
) : GetMovieDetailsUseCase {

    override fun invoke(movieId: String): Flow<Response<MovieDetails>> = movieRepository.getMovieDetails(movieId)

}