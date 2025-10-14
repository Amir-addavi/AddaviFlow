package com.Addavi.addaviflow.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.Addavi.addaviflow.core.HomeScreen
import com.Addavi.addaviflow.core.TestScreen
import com.Addavi.addaviflow.model.BottemNavItem

@Composable
fun SetupNavGraph(navController: NavHostController){
    NavHost(navController = navController , startDestination = BottemNavItem.Home.route){
        composable(BottemNavItem.Home.route){
            HomeScreen()
        }
        composable(BottemNavItem.Setting.route){
            TestScreen()
        }
    }
}