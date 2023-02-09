package io.dvlt.themoviedbtest.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import io.dvlt.themoviedbtest.ui.Navigation
import io.dvlt.themoviedbtest.ui.theme.TheMovieDbTestTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    var keepSplashScreenOpened = true

    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreenOpened
        }

        super.onCreate(savedInstanceState)

        setContent {
            TheMovieDbTestTheme {
                Navigation() {
                    keepSplashScreenOpened = false
                }
            }
        }
    }

}
