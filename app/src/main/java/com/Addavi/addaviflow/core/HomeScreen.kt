package com.Addavi.addaviflow.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp
import com.Addavi.addaviflow.R

@Composable
fun HomeScreen(){
    Box(Modifier.fillMaxSize().background(colorResource(R.color.white)), contentAlignment = Alignment.Center) {
        Text("صفحهٔ خانه", fontSize = 24.sp)
    }
}