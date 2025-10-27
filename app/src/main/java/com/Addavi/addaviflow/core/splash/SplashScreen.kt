package com.Addavi.addaviflow.core.splash

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen
import com.Addavi.addaviflow.R
import com.Addavi.addaviflow.ui.theme.VazirFamily
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
            .background(MaterialTheme.colorScheme.background)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.flow),
                contentDescription = "logo",
                modifier = Modifier
                    .width(120.dp)
            )
            Text(
                text = "Flow Today",
                color = MaterialTheme.colorScheme.surface,
                fontSize = 19.sp,
                fontFamily = VazirFamily,
                fontWeight = FontWeight.Medium
            )
        }
    }
}