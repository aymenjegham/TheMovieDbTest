package com.aymenjegham.core.usecase.getMovieDetails


import com.aymenjegham.core.domain.movieDetails.MovieDetails
import com.aymenjegham.core.helper.Response
import kotlinx.coroutines.flow.Flow

interface GetMovieDetailsUseCase {

    operator fun invoke(movieId :String): Flow<Response<MovieDetails>>
}