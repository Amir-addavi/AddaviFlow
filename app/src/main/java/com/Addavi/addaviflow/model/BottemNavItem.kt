package com.Addavi.addaviflow.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.Addavi.addaviflow.R

sealed class BottemNavItem(
    val route : String,
    val title : String,
    val icon : Int
){
    object Home : BottemNavItem("Home", "Home", R.drawable.home)
    object Setting : BottemNavItem("Setting", "Setting", R.drawable.setting)
}

val bottemNavItem = listOf(
    BottemNavItem.Setting,
    BottemNavItem.Home
)