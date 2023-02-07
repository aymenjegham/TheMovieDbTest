package com.aymenjegham.core.usecase.getAllMovies


import androidx.paging.PagingData
import com.aymenjegham.core.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface GetAllMoviesUseCase {

    operator fun invoke(): Flow<PagingData<Movie>>
}