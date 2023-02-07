package com.aymenjegham.framework.datasource.api

import com.aymenjegham.core.domain.movie.Result
import retrofit2.http.GET
import retrofit2.http.Query


interface APIClient {

    @GET("?seed=foobar&results=30")
    suspend fun getMovies(@Query("page") page: Int) : Result


}