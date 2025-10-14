package com.Addavi.addaviflow.core.splash

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen
import com.Addavi.addaviflow.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimout : () -> Unit){
    val Scale = remember { androidx.compose.animation.core.Animatable(0f)}
    LaunchedEffect(key1 = true) {
        Scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000 , easing = FastOutSlowInEasing)
        )
        delay(1000)
        onTimout()
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(R.color.background))
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo22),
                contentDescription = "logo"
            )
            Text(
                text = "Addavi Flow",
                color = colorResource(R.color.white),
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "How much today",
                color = colorResource(R.color.scend_text),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}