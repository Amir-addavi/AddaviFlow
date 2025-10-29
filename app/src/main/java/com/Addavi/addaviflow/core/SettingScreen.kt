package com.Addavi.addaviflow.core

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.Addavi.addaviflow.R
import com.Addavi.addaviflow.ui.components.DialogLang
import com.Addavi.addaviflow.ui.components.DialogTheme
import com.Addavi.addaviflow.ui.theme.VazirFamily
import com.Addavi.addaviflow.viewmodel.LanguageViewModel

@Composable
fun SettingScreen(navController: NavController, languageViewModel: LanguageViewModel) {
    var showThemSheet by remember { mutableStateOf(false) }
    var showLangDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
                .padding(bottom = 100.dp)

        ) {
            Text(
                text =  stringResource(R.string.setting),
                color = MaterialTheme.colorScheme.surface,
                fontSize = 28.sp,
                fontFamily = VazirFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(vertical = 15.dp)
            )
            Divider(
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                thickness = 1.5.dp,
            )
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(top = 25.dp)
            ) {
                SettingItems(1, R.drawable.theme_ico ,  stringResource(R.string.setting_theme)) { showThemSheet = true }
                SettingItems(3, R.drawable.language_ico ,  stringResource(R.string.Language)) { showLangDialog = true }
                SettingItems(2, R.drawable.info_ico, stringResource(R.string.setting_about)) { navController.navigate("info") }
            }
        }
        if (showThemSheet) {
            DialogTheme(
                onDismiss = { showThemSheet = false },
                languageViewModel = languageViewModel
            )
        }
        if (showLangDialog){
            DialogLang(
                onDismiss = { showLangDialog = false },
                languageViewModel = languageViewModel
            )
        }
    }
}

@Composable
fun SettingItems(Id: Int, pic: Int,name: String, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val scale by animateFloatAsState(
        targetValue = if (isPressed) 0.95f else 1f,
        animationSpec = tween(500)
    )
    val alpha by animateFloatAsState(
        targetValue = if(isPressed) 0.7f else 1f,
        animationSpec = tween(500)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .scale(scale)
            .clickable(
                onClick = { onClick() },
                interactionSource = interactionSource,
                indication = null
            )
            .padding(vertical = 10.dp)
            .fillMaxWidth()
            .alpha(alpha)
            .height(50.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.15f))
            .padding(start = 5.dp, end = 8.dp)
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
                Icon(
                    painter = painterResource(pic),
                    contentDescription = "icon",
                    tint = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.size(23.dp)
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = name,
                fontSize = 18.sp,
                fontFamily = VazirFamily,
                color = MaterialTheme.colorScheme.surface,
                fontWeight = FontWeight.Medium
            )
        }
        Icon(
            painter = painterResource(R.drawable.arrow_ico),
            contentDescription = "icon",
            tint = MaterialTheme.colorScheme.surface,
            modifier = Modifier.size(27.dp)
        )
    }
}
