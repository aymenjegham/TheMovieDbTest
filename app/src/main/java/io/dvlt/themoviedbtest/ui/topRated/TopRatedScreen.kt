package io.dvlt.themoviedbtest.ui.topRated

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import io.dvlt.themoviedbtest.R
import io.dvlt.themoviedbtest.ui.ScreenRoutes
import io.dvlt.themoviedbtest.ui.composable.RatingBar


@Composable
fun TopRatedScreen(
    topRatedViewModel: TopRatedViewModel = hiltViewModel(),
    onNavigate: (ScreenRoutes) -> Unit,
) {

    val movies = topRatedViewModel.fetchTopRatedMovies().collectAsLazyPagingItems()


    LazyColumn() {
        items(
            items = movies,
        ) { movie ->
            movie?.let {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .testTag("row")
                        .clickable {
                        onNavigate(ScreenRoutes.DetailScreen(movie.id))
                    }
                ) {
                    var isImageLoading by remember { mutableStateOf(false) }

                    val painter = rememberAsyncImagePainter(
                        model = movie.posterPathFull,
                    )

                    isImageLoading = when (painter.state) {
                        is AsyncImagePainter.State.Loading -> true
                        else -> false
                    }

                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 3.dp)
                                .height(115.dp)
                                .width(77.dp)
                                .clip(RoundedCornerShape(8.dp)),
                            painter = painter,
                            contentDescription = stringResource(R.string.poster_image),
                            contentScale = ContentScale.FillBounds,
                        )

                        if (isImageLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .padding(horizontal = 6.dp, vertical = 3.dp),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                    }
                    Column(
                        Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(
                                    vertical = 18.dp,
                                    horizontal = 8.dp,
                                ),
                            color = MaterialTheme.colorScheme.secondary,
                            text = it.title ?: stringResource(R.string.not_available)
                        )

                        RatingBar(
                            modifier = Modifier
                                .padding(
                                    horizontal = 8.dp,
                                ),
                            rating = it.voteAverage.toFloat(),
                            spaceBetween = 3.dp,
                            totalStarCount = 10
                        )

                    }
                }
                Divider()
            }

        }

        val loadState = movies.loadState.mediator

        item {

            if (loadState?.refresh == LoadState.Loading) {
                Column(
                    modifier = Modifier
                        .fillParentMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = stringResource(R.string.refresh_loading)
                    )

                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            if (movies.loadState.append == LoadState.Loading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }

            if (loadState?.refresh is LoadState.Error || loadState?.append is LoadState.Error) {
                val isPaginatingError =
                    (loadState.append is LoadState.Error) || movies.itemCount > 1
                val error = if (loadState.append is LoadState.Error)
                    (loadState.append as LoadState.Error).error
                else
                    (loadState.refresh as LoadState.Error).error

                val modifier = if (isPaginatingError) {
                    Modifier.padding(8.dp)
                } else {
                    Modifier.fillParentMaxSize()
                }
                Column(
                    modifier = modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                )
                {
                    if (!isPaginatingError) {
                        Icon(
                            modifier = Modifier
                                .size(64.dp),
                            imageVector = Icons.Rounded.Warning, contentDescription = null
                        )
                    }

                    Text(
                        modifier = Modifier
                            .padding(8.dp),
                        text = error.message ?: error.toString(),
                        textAlign = TextAlign.Center,
                    )

                    Button(
                        onClick = {
                            movies.refresh()
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


}



