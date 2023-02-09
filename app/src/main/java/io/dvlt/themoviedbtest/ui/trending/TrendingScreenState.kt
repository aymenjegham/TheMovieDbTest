package io.dvlt.themoviedbtest.ui.trending

import com.aymenjegham.core.domain.movie.Movie
import java.io.Serializable


data class TrendingScreenState(
    val movies: List<Movie> = emptyList(),
    val showLoader: Boolean = false,
    val error: String? = null,
) : Serializable
