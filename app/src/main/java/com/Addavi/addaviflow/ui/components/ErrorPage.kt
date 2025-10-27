package com.Addavi.addaviflow.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Addavi.addaviflow.ui.theme.VazirFamily

@Composable
fun ErrorPage(pic: Int, name: String, desc: String, btnText: String, onclick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 22.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(10.dp)
            .padding(vertical = 40.dp)
    ) {
        Image(
            painter = painterResource(pic),
            contentDescription = "errorIcon",
            modifier = Modifier.width(130.dp)
        )
        Spacer(modifier = Modifier.height(25.dp))
        Text(
            text = name,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = VazirFamily,
            color = MaterialTheme.colorScheme.error
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = desc,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSurface,
            fontFamily = VazirFamily,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextButton(
            onClick = onclick,
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            ),
            modifier = Modifier
                .width(150.dp)
        ) {
            Text(
                text = btnText,
                fontFamily = VazirFamily,
            )
        }
    }
}