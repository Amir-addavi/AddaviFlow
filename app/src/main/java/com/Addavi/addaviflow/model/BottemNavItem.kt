package com.Addavi.addaviflow.model

import com.Addavi.addaviflow.R

sealed class BottemNavItem(
    val route : String,
    val title : String,
    val icon : Int,
    val selectIcon : Int
){
    object Home : BottemNavItem("Home", "Home", R.drawable.home2 , R.drawable.home_selected)
    object Setting : BottemNavItem("Setting", "Setting", R.drawable.setting2 , R.drawable.setting_selected)
}

val bottemNavItem = listOf(
    BottemNavItem.Setting,
    BottemNavItem.Home
)