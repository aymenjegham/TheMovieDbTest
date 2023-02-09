package io.dvlt.themoviedbtest.ui.details

import com.aymenjegham.core.domain.movieDetails.MovieDetails
import java.io.Serializable


data class MovieDetailsScreenState(
    val movieDetails: MovieDetails?=null,
    val showLoader: Boolean = false,
    val error: String? = null,
) : Serializable
