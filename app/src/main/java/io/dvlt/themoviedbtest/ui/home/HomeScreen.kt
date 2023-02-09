package io.dvlt.themoviedbtest.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import io.dvlt.themoviedbtest.R
import io.dvlt.themoviedbtest.ui.ScreenRoutes
import io.dvlt.themoviedbtest.ui.composable.Tabs
import io.dvlt.themoviedbtest.ui.composable.Toolbar
import io.dvlt.themoviedbtest.ui.topRated.TopRatedScreen
import io.dvlt.themoviedbtest.ui.trending.TrendingScreen


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = hiltViewModel(), onNavigate: (ScreenRoutes) -> Unit) {

    val pagerState = homeViewModel.pagerState

    Column {
        Toolbar(stringResource(R.string.toolbar_label))
        TabScreen(pagerState,onNavigate)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabScreen(pagerState: PagerState, onNavigate: (ScreenRoutes) -> Unit) {

    val tabLabels = listOf(stringResource(R.string.trending), stringResource(R.string.top_rated))

    Column(
        modifier = Modifier.background(MaterialTheme.colorScheme.background)
    )
    {
        Tabs(pagerState = pagerState, tabLabels)
        TabsContent(pagerState = pagerState, tabLabels,onNavigate)
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabsContent(pagerState: PagerState, tabLabels: List<String>, onNavigate: (ScreenRoutes) -> Unit) {

    HorizontalPager( pageCount = tabLabels.size, state = pagerState) { page ->
        when (page) {
            0 -> TrendingScreen(onNavigate=onNavigate)
            1 -> TopRatedScreen(onNavigate=onNavigate)
        }
    }
}



