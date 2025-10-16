package com.Addavi.addaviflow.core

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Addavi.addaviflow.R

@Composable
fun TestScreen() {
    Scaffold(
        modifier = Modifier
            .background(colorResource(R.color.background))
            .statusBarsPadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(colorResource(R.color.background))
                .padding(horizontal = 16.dp)
                .padding(bottom = 100.dp)

        ) {
            Text(
                text = "Setting",
                fontSize = 28.sp,
                color = colorResource(R.color.primary_text),
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(vertical = 15.dp)
            )
            Divider(
                color = colorResource(R.color.scend_text).copy(alpha = 0.3f),
                thickness = 1.5.dp,
            )
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 25.dp)
            ) {
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
                SettingItems(1 , "Theme")
            }
        }
    }
}

@Composable
fun SettingItems(Id: Int, name: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(colorResource(R.color.primary_text).copy(alpha = 0.15f))
            .padding(horizontal = 15.dp)
    ) {
        Text(
            text = name,
            fontSize = 18.sp,
            color = colorResource(R.color.scend_text),
            fontWeight = FontWeight.Bold
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .width(70.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(colorResource(R.color.primary).copy(alpha = 0.2f))
                .padding(vertical = 2.dp)
        ) {
            Text(
                text = "Change",
                color = colorResource(R.color.primary)
            )
        }
    }
}