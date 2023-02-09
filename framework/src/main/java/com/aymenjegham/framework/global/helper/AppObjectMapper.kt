package com.aymenjegham.framework.global.helper



import com.aymenjegham.core.domain.movie.Movie
import com.aymenjegham.core.domain.movieDetails.MovieDetails
import com.aymenjegham.core.helper.ObjectMapper
import com.aymenjegham.framework.entity.MovieDetailsEntity
import com.aymenjegham.framework.entity.MovieEntity
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.reflect.KClass

@Singleton
class AppObjectMapper @Inject constructor(gson: Gson) : ObjectMapper(gson) {


    override val supportedMapping: Map<KClass<out Any>, KClass<out Any>>
        get() = mapOf(
            MovieEntity::class to Movie::class,
            Movie::class to MovieEntity::class,
            MovieDetailsEntity::class to MovieDetails::class,
            MovieDetails::class to MovieDetailsEntity::class,
        )
}