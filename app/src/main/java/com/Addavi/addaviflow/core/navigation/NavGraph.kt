package com.Addavi.addaviflow.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.Addavi.addaviflow.core.HomeScreen
import com.Addavi.addaviflow.core.InfoScreen
import com.Addavi.addaviflow.core.SearchScreen
import com.Addavi.addaviflow.core.SettingScreen
import com.Addavi.addaviflow.model.BottemNavItem
import com.Addavi.addaviflow.viewmodel.LanguageViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(navController: NavHostController , languageViewModel: LanguageViewModel) {
    NavHost(navController = navController, startDestination = BottemNavItem.Home.route)  {
            composable(
                route = BottemNavItem.Home.route,
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(400)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(400)) },
            ) {
                HomeScreen(navController = navController)
            }

            composable(
                route = BottemNavItem.Setting.route,
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(400)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(400)) },
            ) {
                SettingScreen(navController, languageViewModel)
            }

            composable(
                route = "info",
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(400)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(400)) },
            ) {
                InfoScreen(navController = navController)
            }

            composable(
                route = BottemNavItem.Search.route,
                enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Up, tween(400)) },
                exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Down, tween(400)) },
            ) {
                SearchScreen()
            }
        }
    }