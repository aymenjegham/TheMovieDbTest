package io.dvlt.themoviedbtest.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.dvlt.themoviedbtest.ui.details.DetailScreen
import io.dvlt.themoviedbtest.ui.home.HomeScreen

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ScreenRoutes.HomeScreen.route,
    onComposableReady: () -> Unit,
) {
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        onComposableReady()
    }

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = ScreenRoutes.HomeScreen.route) {
            HomeScreen() { destination ->
                when (destination) {
                    is ScreenRoutes.DetailScreen -> {
                        navController.navigate("details/${destination.route}")
                    }
                    else -> {}
                }

            }
        }
        composable(route = "details/{movieId}") {
            DetailScreen(arg = it.arguments?.getString("movieId"))
        }
    }

}

sealed class ScreenRoutes(val route: String) {
    object HomeScreen : ScreenRoutes("main")
    data class DetailScreen(var arg: Int) : ScreenRoutes(arg.toString())
}