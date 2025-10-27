package com.Addavi.addaviflow.model

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.Addavi.addaviflow.R



sealed class BottemNavItem(
    val route : String,
    @StringRes val  title : Int,
    val icon : Int,
    val selectIcon : Int
){
    object Home : BottemNavItem("Home", R.string.home, R.drawable.home2 , R.drawable.home_selected)
    object Setting : BottemNavItem("Setting", R.string.setting_nav, R.drawable.setting2 , R.drawable.setting_selected)
}

val bottemNavItem = listOf(
    BottemNavItem.Setting,
    BottemNavItem.Home
)