package com.Addavi.addaviflow.model

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