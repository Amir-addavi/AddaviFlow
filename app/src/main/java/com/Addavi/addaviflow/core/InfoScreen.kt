package com.Addavi.addaviflow.core

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.Addavi.addaviflow.R
import androidx.core.net.toUri
import com.Addavi.addaviflow.ui.theme.VazirFamily

@Composable
fun InfoScreen(navController: NavHostController ) {
    val context = LocalContext.current
    val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
    val versionName = packageInfo.versionName
    val clipboardManager = LocalClipboardManager.current


    val githubUrl = "https://github.com/Amir-addavi/"
    val linkedinUrl = "https://www.linkedin.com/in/amirali-addavi"
    val emailAddress = "amirali.addavi@gmail.com"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 17.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    painter = painterResource(R.drawable.back_ico),
                    contentDescription = "icon",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .size(35.dp)
                )
            }
                Text(
                    text =  stringResource(R.string.about),
                    color = MaterialTheme.colorScheme.surface,
                    fontSize = 28.sp,
                    fontFamily = VazirFamily,
                    fontWeight = FontWeight.Bold,
                )
                Icon(
                    painter = painterResource(R.drawable.info_ico),
                    contentDescription = "icon",
                    tint = MaterialTheme.colorScheme.background,
                    modifier = Modifier.size(35.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.flow),
                    contentDescription = "icon",
                    modifier = Modifier
                        .width(80.dp)
                        .clip(RoundedCornerShape(30.dp))
                        .background(MaterialTheme.colorScheme.onPrimary)
                )
                Spacer(modifier = Modifier.width(15.dp))
                Column(verticalArrangement = Arrangement.Center) {
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                        Text(
                            text =  stringResource(R.string.about_name),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = VazirFamily,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text =  stringResource(R.string.app_name),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = VazirFamily,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                        Text(
                            text = stringResource(R.string.about_version),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = VazirFamily,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text = versionName.toString(),
                            fontSize = 13.sp,
                            fontFamily = VazirFamily,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    Row(modifier = Modifier.padding(vertical = 2.dp)) {
                        Text(
                            text =  stringResource(R.string.about_dev),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = VazirFamily,
                            color = MaterialTheme.colorScheme.surface
                        )
                        Text(
                            text =  stringResource(R.string.about_dev_name),
                            fontSize = 13.sp,
                            fontFamily = VazirFamily,
                            fontWeight = FontWeight.Medium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                modifier = Modifier
                    .padding(vertical = 25.dp)
                    .width(100.dp)
            )
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text =  stringResource(R.string.about_about_us),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = VazirFamily,
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Text(
                    text = stringResource(R.string.info_text),
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurface,
                    lineHeight = 20.sp,
                    fontFamily = VazirFamily,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                modifier = Modifier
                    .padding(vertical = 25.dp)
                    .width(100.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Text(
                    text = stringResource(R.string.about_contcat_us),
                    fontFamily = VazirFamily,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.surface,
                )
                ContactUs(R.drawable.github_ico, "GitHub") {
                    safeOpenUrl(context, githubUrl)
                }

                ContactUs(R.drawable.linkedin_ico, "LinkedIn") {
                    safeOpenUrl(context, linkedinUrl)
                }
                ContactUs(R.drawable.email_ico, "Email") {
                    clipboardManager.setText(androidx.compose.ui.text.AnnotatedString(emailAddress))
                    Toast
                        .makeText(context, "Copied!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}

@Composable
fun ContactUs(pic: Int, name: String, link:  () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.15f))
            .padding(start = 5.dp, end = 8.dp)
            .clickable { link() }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.background)
                    .padding(10.dp)
            ) {
                androidx.compose.material3.Icon(
                    painter = painterResource(pic),
                    contentDescription = "icon",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(23.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            androidx.compose.material3.Text(
                text = name,
                fontFamily = VazirFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.surface,
                fontWeight = FontWeight.Medium,
            )
        }
        androidx.compose.material3.Icon(
            painter = painterResource(R.drawable.arrow_ico),
            contentDescription = "icon",
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier.size(27.dp)
        )
    }
}

fun safeOpenUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        val resolveInfo = context.packageManager.resolveActivity(intent, 0)
        if (resolveInfo != null) {
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "مرورگری برای باز کردن لینک پیدا نشد.", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        e.printStackTrace()
        Toast.makeText(context, "خطا در باز کردن لینک.", Toast.LENGTH_SHORT).show()
    }
}