package com.aymenjegham.framework.data.repositoryImpl

import androidx.paging.*
import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.core.domain.movie.Movie
import com.aymenjegham.framework.database.Database
import com.aymenjegham.framework.datasource.api.APIClient
import com.aymenjegham.framework.datasource.database.MovieDatabase
import com.aymenjegham.framework.global.helper.AppObjectMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val api: APIClient,
    private val movieDatabase: MovieDatabase,
    private val mapper: AppObjectMapper,
    private val database: Database,
) : MovieRepository {

    private val pageSize = 100

    @OptIn(
        ExperimentalPagingApi::class,
        ExperimentalCoroutinesApi::class
    )
    override fun getMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = pageSize,
                prefetchDistance = 10,
                initialLoadSize = pageSize,
            ),
            pagingSourceFactory = {
                movieDatabase.getAll()
            },
            remoteMediator = MoviesRemoteMediator(
                api,
                database,
                mapper
            )
        ).flow.mapLatest {
            it.map { movieEntity ->
                mapper.map(movieEntity, Movie::class)
            }
        }.flowOn(Dispatchers.IO)

}