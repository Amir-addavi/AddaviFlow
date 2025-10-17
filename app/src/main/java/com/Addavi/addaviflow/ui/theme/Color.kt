package com.Addavi.addaviflow.ui.theme


import androidx.compose.material3.darkColorScheme
import  com.Addavi.addaviflow.R.color.bottomNav_light
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.Addavi.addaviflow.R

val primary_light = Color(0xFF18C267)
val onPrimary_light = Color(0xFFD5D5D5)
val background_light = Color(0xFFFFFFFF)
val onPrimaryText_light = Color(0xFF000000)
val secendryText_light = Color(0xFF7C7C7C)

val primary_dark = Color(0xFF18C267)
val onPrimary_dark = Color(0xFF2C2C2C)
val background_dark = Color(0xFF000000)
val onPrimaryText_dark= Color(0xFFFFFFFF)
val secendryText_dark= Color(0xFF737373)

val LightColor = lightColorScheme(
    primary = primary_light,
    onPrimary = onPrimary_light,
    background = background_light,
    surface = onPrimaryText_light,
    onSurface = secendryText_light
)

val DarkColor = darkColorScheme(
    primary = primary_dark,
    onPrimary = onPrimary_dark,
    background = background_dark,
    surface = onPrimaryText_dark,
    onSurface = secendryText_dark
)