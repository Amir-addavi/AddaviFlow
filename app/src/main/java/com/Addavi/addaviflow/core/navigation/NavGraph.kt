package com.Addavi.addaviflow.core.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.Addavi.addaviflow.core.HomeScreen
import com.Addavi.addaviflow.core.InfoScreen
import com.Addavi.addaviflow.core.TestScreen
import com.Addavi.addaviflow.model.BottemNavItem
import com.Addavi.addaviflow.ui.components.WebViewScreen
import com.Addavi.addaviflow.viewmodel.LanguageViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController , languageViewModel: LanguageViewModel) {
    NavHost(navController = navController, startDestination = BottemNavItem.Home.route) {
        composable(
            BottemNavItem.Home.route,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(600))
            }, exitTransition = {
                slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(600))
            }
        ) {
            HomeScreen()
        }
        composable(BottemNavItem.Setting.route ,
                enterTransition = {
            slideInHorizontally(initialOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(600))
        }, exitTransition = {
            slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth },
                animationSpec = tween(600))
        }
        ) {
            TestScreen(navController , languageViewModel)
        }
        composable("info" ,
                enterTransition = {
            slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(600))
        }, exitTransition = {
            slideOutHorizontally(targetOffsetX = { fullWidth -> fullWidth },
                animationSpec = tween(600))
        }
        ) { InfoScreen(navController = navController) }
        composable("webview/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url") ?: ""
            WebViewScreen(url = url , navController)
        }
    }
}

/*
 */