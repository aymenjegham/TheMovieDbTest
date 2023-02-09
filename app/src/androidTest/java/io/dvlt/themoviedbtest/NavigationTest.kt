package io.dvlt.themoviedbtest

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.dvlt.themoviedbtest.ui.Navigation
import io.dvlt.themoviedbtest.ui.ScreenRoutes
import io.dvlt.themoviedbtest.ui.main.MainActivity
import io.dvlt.themoviedbtest.utils.withRole
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class NavigationTest {


    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var navController: TestNavHostController

    @Before
    fun setupAppNavHost() {
        hiltRule.inject()
        composeRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            Navigation(navController = navController, onComposableReady = {})
        }
    }


    @Test
    fun navHost_verifyStartDestination() {
        val trending = composeRule.activity.getString(R.string.trending)

        composeRule
            .onNode(withRole(Role.Tab).and(hasText(trending)))
            .assertIsDisplayed()
    }

    @Test
    fun navHost_clickItem_navigateToDetailsScreen() {

         composeRule.onNodeWithTag("row").performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        assertEquals(route, ScreenRoutes.HomeScreen.route)
    }
}