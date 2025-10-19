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
import com.Addavi.addaviflow.core.TestScreen
import com.Addavi.addaviflow.model.BottemNavItem

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController) {
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
            TestScreen()
        }
    }
}

/*
 */