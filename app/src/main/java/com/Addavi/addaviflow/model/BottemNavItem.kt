package com.Addavi.addaviflow.model

import androidx.annotation.StringRes
import com.Addavi.addaviflow.R


sealed class BottemNavItem(
    val route : String,
    val  title : String,
    val icon : Int,
    val selectIcon : Int
){
    object Search : BottemNavItem("Search", "", R.drawable.search_ico , R.drawable.search_ico)
    object Home : BottemNavItem("Home", "", R.drawable.home2 , R.drawable.home_selected)
    object Setting : BottemNavItem("Setting", "", R.drawable.setting2 , R.drawable.setting_selected)
}

val BottomNavItem = listOf(
    BottemNavItem.Setting,
    BottemNavItem.Home,
    BottemNavItem.Search
)