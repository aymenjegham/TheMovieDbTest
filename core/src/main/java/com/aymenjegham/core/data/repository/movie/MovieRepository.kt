package com.aymenjegham.core.data.repository.movie

import androidx.paging.PagingData
import com.aymenjegham.core.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMovies(): Flow<PagingData<Movie>>

}