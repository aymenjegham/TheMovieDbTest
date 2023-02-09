package com.aymenjegham.framework.data.repositoryImpl

import android.content.Context
import androidx.paging.*
import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.core.domain.movie.Movie
import com.aymenjegham.core.domain.movie.Result
import com.aymenjegham.core.domain.movieDetails.MovieDetails
import com.aymenjegham.core.helper.Response
import com.aymenjegham.framework.database.Database
import com.aymenjegham.framework.datasource.api.APIClient
import com.aymenjegham.framework.datasource.database.movie.MovieDatabase
import com.aymenjegham.framework.datasource.database.movieDetails.MovieDetailsDatabase
import com.aymenjegham.framework.di.dispatcher.DispatcherProvider
import com.aymenjegham.framework.entity.MovieDetailsEntity
import com.aymenjegham.framework.entity.MovieEntity
import com.aymenjegham.framework.global.extension.isNetworkAvailable
import com.aymenjegham.framework.global.helper.AppObjectMapper
import com.aymenjegham.framework.global.utils.ErrorUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import io.dvlt.themoviedbtest.framework.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import retrofit2.Retrofit
import javax.inject.Inject


class MovieRepositoryImpl @Inject constructor(
    private val api: APIClient,
    private val movieDatabase: MovieDatabase,
    private val movieDetailsDatabase: MovieDetailsDatabase,
    private val mapper: AppObjectMapper,
    private val database: Database,
    private val retrofit: Retrofit,
    private val dispatchers: DispatcherProvider,
    @ApplicationContext private val context: Context,
) : MovieRepository {


    @OptIn(
        ExperimentalPagingApi::class,
        ExperimentalCoroutinesApi::class
    )
    override fun getTopRatedMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
            ),
            pagingSourceFactory = {
                movieDatabase.getTopRated()
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
        }.flowOn(dispatchers.io)

    override fun getTrendingMovies(): Flow<Response<List<Movie>>> {
        return flow {

            emit(Response.loading())
            val result = getMovieResponse(
                request = { api.getTrendingMovies() },
                defaultErrorMessage = context.getString(R.string.error_fetching_movies_list)
            )

            //Cache to database if response is successful
            when(result.status){
                Response.Status.SUCCESS ->{
                    result.data?.let { movies ->
                        movies.map { movie ->
                            mapper.map(movie, MovieEntity::class).copy(trending = true)
                        }.run {
                            movieDatabase.deleteAllTrendy()
                            movieDatabase.insertAll(this)
                        }
                    }
                    emit(fetchCachedMovies())
                }
                else -> {
                    emit(Response.error(result.message?:"No Error Message",result.error))
                }
            }


        }.flowOn(dispatchers.io)
    }

    override fun getMovieDetails(movieId: String): Flow<Response<MovieDetails>> {
        return flow {

            emit(Response.loading())
            val result = getMovieDetailsResponse(
                request = { api.getMovieDetails(movieId) },
                defaultErrorMessage = context.getString(R.string.error_fetching_movies_list)
            )

            when(result.status){
                Response.Status.SUCCESS -> {
                    result.data?.let { movieDetails ->

                        mapper.map(movieDetails, MovieDetailsEntity::class).run {
                            movieDetailsDatabase.insertMovieDetails(this)
                        }
                    }
                    emit(fetchCachedMovieDetail(movieId))
                }
                else -> emit(Response.error(result.message?:"No Error Message",result.error))
            }

        }.flowOn(dispatchers.io)
    }

    private suspend fun getMovieResponse(
        request: suspend () -> retrofit2.Response<Result>,
        defaultErrorMessage: String,
    ): Response<List<Movie>> {
        return try {
            val result = request()
            if (result.isSuccessful) {
                return Response.success(result.body()?.movies)
            } else {
                val errorResponse =
                    ErrorUtils.parseError(result, retrofit)
                Response.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            if (context.isNetworkAvailable()) {
                Response.error("Unknown Error ", null)
            } else {
                Response.error("No network available", null)
            }

        }
    }

    private suspend fun getMovieDetailsResponse(
        request: suspend () -> retrofit2.Response<MovieDetails>,
        defaultErrorMessage: String,
    ): Response<MovieDetails> {
        return try {
            val result = request()
            if (result.isSuccessful) {
                return Response.success(result.body())
            } else {
                val errorResponse =
                    ErrorUtils.parseError(result, retrofit)
                Response.error(errorResponse?.status_message ?: defaultErrorMessage, errorResponse)
            }
        } catch (e: Throwable) {
            if (context.isNetworkAvailable()) {
                Response.error("Unknown Error ", null)
            } else {
                Response.error("No network available", null)
            }

        }
    }

    private fun fetchCachedMovies(): Response<List<Movie>> =
        movieDatabase.getTrendy().let { movieEntities ->
            movieEntities.map { movieEntity ->
                mapper.map(movieEntity, Movie::class)
            }.run {
                Response.success(this)
            }
        }

    private fun fetchCachedMovieDetail(movieId: String): Response<MovieDetails> =
        movieDetailsDatabase.getMovieDetails(movieId).let { movieDetailEntity ->
            mapper.map(movieDetailEntity, MovieDetails::class)
                .run {
                    Response.success(this)
                }
        }


}