package com.aymenjegham.framework.datasource.api

import com.aymenjegham.core.domain.movie.Result
import com.aymenjegham.core.domain.movieDetails.MovieDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIClient {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int,
    ): Result

    @GET("trending/all/day")
    suspend fun getTrendingMovies(): Response<Result>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: String,
    ): Response<MovieDetails>

}