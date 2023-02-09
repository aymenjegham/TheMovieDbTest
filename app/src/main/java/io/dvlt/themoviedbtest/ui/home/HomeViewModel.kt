package io.dvlt.themoviedbtest.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    @OptIn(ExperimentalFoundationApi::class)
    val pagerState = PagerState(0)


}