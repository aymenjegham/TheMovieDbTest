package io.dvlt.themoviedbtest

import androidx.paging.PagingData
import com.aymenjegham.core.data.repository.movie.MovieRepository
import com.aymenjegham.core.domain.Error
import com.aymenjegham.core.domain.movie.Movie
import com.aymenjegham.core.domain.movieDetails.MovieDetails
import com.aymenjegham.core.helper.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeMovieRepository @Inject constructor(
) : MovieRepository {

    private val fakeListOfMovieItems = listOf(
        Movie(
            id = 0,
            trending =true ,
            adult =false ,
            backdropPath = "url/photo",
            originalLanguage = "fr",
            originalTitle = "l'oiseau",
            overview ="un oiseau entre dans ma chambre",
            page = 1,
            popularity =1245.0 ,
            posterPath = "url/poster",
            releaseDate = "02/04/1952",
            title = "the bird is the word",
            video = false,
            voteAverage =8.01 ,
            voteCount = 120
        ),
        Movie(
            id = 1,
            trending =false ,
            adult =true ,
            backdropPath = "url/photo2",
            originalLanguage = "en",
            originalTitle = "les oiseaux",
            overview ="un canari entre dans ma chambre",
            page = 2,
            popularity =2520.0 ,
            posterPath = "url/poster2",
            releaseDate = "02/04/1956",
            title = "the bird ",
            video = true,
            voteAverage =7.01 ,
            voteCount = 180
        ),
    )
    private val response = Response(Response.Status.SUCCESS, fakeListOfMovieItems, null, null)
    private val responseFailure = Response(Response.Status.ERROR, emptyList<Movie>(), Error(status_message = "No network available"), null)


    override fun getTopRatedMovies(): Flow<PagingData<Movie>> {
        return flow {

                //need to create a testing mock for pagingData
                //emit(responseFailure)
            }
    }

    override fun getTrendingMovies(): Flow<Response<List<Movie>>> {
        return emptyFlow()
    }

    override fun getMovieDetails(movieId: String): Flow<Response<MovieDetails>> {
        return emptyFlow()
    }

}