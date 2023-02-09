package io.dvlt.themoviedbtest.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import io.dvlt.themoviedbtest.R
import io.dvlt.themoviedbtest.ui.composable.MovieDetailsInfo
import io.dvlt.themoviedbtest.ui.composable.RatingBar
import io.dvlt.themoviedbtest.ui.composable.Toolbar

@Composable
fun DetailScreen(detailsViewModel: DetailsViewModel = hiltViewModel(), arg: String?) {


    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        arg?.let {
            detailsViewModel.fetchMovieDetails(arg)
        }
    }

    val state = detailsViewModel.movieDetailsUiState

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Toolbar(state.movieDetails?.title ?: stringResource(id = R.string.details))

            Row {
                Image(
                    painter = rememberAsyncImagePainter(
                        state.movieDetails?.posterPathFull,
                        error = painterResource(R.drawable.ic_star)
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,

                    modifier = Modifier
                        .padding(bottom = 8.dp, top = 16.dp, start = 8.dp)
                        .height(250.dp)
                        .width(190.dp)
                        .clip(RoundedCornerShape(16.dp)),
                )

                Column() {
                    Text(
                        modifier = Modifier.padding(start = 8.dp, top = 16.dp, bottom = 8.dp),
                        text = state.movieDetails?.title
                            ?: stringResource(id = R.string.not_available),
                        fontSize = 26.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )
                    MovieDetailsInfo(
                        stringResource(R.string.release_date),
                        state.movieDetails?.releaseDate ?: "--:--"
                    )
                    MovieDetailsInfo(
                        stringResource(R.string.original_language),
                        state.movieDetails?.originalLanguage ?: stringResource(id = R.string.not_available)
                    )

                }
            }

            state.movieDetails?.let { movieDetails ->
                Column(
                    modifier = Modifier.padding(end = 8.dp, start = 8.dp)
                )
                {

                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp,
                                vertical = 8.dp
                            ),
                        text = "${movieDetails.voteCount} voters",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    )

                    RatingBar(
                        modifier = Modifier
                            .padding(
                                horizontal = 8.dp,
                                vertical = 4.dp
                            ),
                        rating = movieDetails.voteAverage.toFloat(),
                        spaceBetween = 3.dp,
                        totalStarCount = 10
                    )


                    Text(
                        modifier = Modifier.padding(
                            bottom = 8.dp,
                            top = 16.dp,
                            start = 8.dp,
                            end = 8.dp
                        ),
                        text = stringResource(id = R.string.overview),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.primary
                    )

                    movieDetails.overview?.let { overView ->
                        Text(
                            modifier =
                            Modifier
                                .padding(
                                    bottom = 8.dp,
                                    top = 4.dp,
                                    start = 8.dp,
                                    end = 8.dp
                                )
                                .verticalScroll(rememberScrollState()),
                            text = overView,
                            color = MaterialTheme.colorScheme.secondary

                        )
                    }

                }
            }


        }

        if (state.showLoader) CircularProgressIndicator(modifier = Modifier.testTag("loader"))


        if (state.error != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(64.dp),
                    imageVector = Icons.Rounded.Warning, contentDescription = null
                )

                Text(
                    modifier = Modifier
                        .padding(8.dp),
                    text = state.error,
                    textAlign = TextAlign.Center,
                )

                Button(
                    onClick = {
                        arg?.let {
                            detailsViewModel.fetchMovieDetails(arg)
                        }
                    },
                    content = {
                        Text(text = stringResource(R.string.refresh))
                    },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = Color.White,
                    )
                )
            }
        }

    }


}
