package io.dvlt.themoviedbtest.ui.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(pagerState: PagerState, tabLabels: List<String>) {


    val scope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        contentColor = MaterialTheme.colorScheme.surface,
    ) {
        tabLabels.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        text = tabLabels[index],
                        color = if (pagerState.currentPage == index) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary
                    )
                },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }

    }

}