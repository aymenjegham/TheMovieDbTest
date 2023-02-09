package io.dvlt.themoviedbtest.ui.trending

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.dvlt.themoviedbtest.R
import io.dvlt.themoviedbtest.ui.main.MainActivity
import io.dvlt.themoviedbtest.ui.theme.TheMovieDbTestTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class TrendingScreenKtTest {
    private lateinit var trendingViewModel: TrendingViewModel

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        hiltRule.inject()

        composeRule.activity.setContent {
            TheMovieDbTestTheme {
                trendingViewModel = hiltViewModel()
                TrendingScreen(trendingViewModel) {}
            }
        }
    }

    @Test
    fun homeScreenInitialAppearance() {
        val trending = composeRule.activity.getString(R.string.trending)
        val topRated = composeRule.activity.getString(R.string.top_rated)

        composeRule.onNodeWithText(trending).assertExists()
        composeRule.onNodeWithText(topRated).assertExists()
    }
}