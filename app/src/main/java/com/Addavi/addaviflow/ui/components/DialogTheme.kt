package com.Addavi.addaviflow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.Addavi.addaviflow.R
import com.Addavi.addaviflow.ui.theme.ThemeMode
import com.Addavi.addaviflow.ui.theme.VazirFamily
import com.Addavi.addaviflow.util.LocaleHelper
import com.Addavi.addaviflow.viewmodel.LanguageViewModel
import com.Addavi.addaviflow.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch

@Composable
fun DialogTheme(
    onDismiss: () -> Unit,
    languageViewModel: LanguageViewModel,
    themeViewModel: ThemeViewModel = viewModel() ,
) {
    val scope = rememberCoroutineScope()
    val currentMode by themeViewModel.themeModeState.collectAsState()
    val currentLang by languageViewModel.language.collectAsState()
    val baseContext = LocalContext.current
    val localizedContext = remember(currentLang) {
        LocaleHelper.setLocale(baseContext, currentLang)
    }
    key(currentLang) {
        CompositionLocalProvider(LocalContext provides localizedContext) {
            Dialog(onDismissRequest = onDismiss) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .padding(20.dp)
                ) {
                    Text(
                        text = if (currentLang == "fa") "انتخاب پوسته" else "Select Theme",
                        fontFamily = VazirFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.surface
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    ThemeOptionsRowTheme(
                        label = if (currentLang == "fa") "روشن" else "Light",
                        selected = currentMode == ThemeMode.LIGHT,
                        onClick = {
                            themeViewModel.setTheme(ThemeMode.LIGHT)
                            scope.launch {
                                onDismiss()
                            }
                        }
                    )
                    ThemeOptionsRowTheme(
                        label = if (currentLang == "fa") "تاریک" else "Dark",
                        selected = currentMode == ThemeMode.DARK,
                        onClick = {
                            themeViewModel.setTheme(ThemeMode.DARK)
                            scope.launch {
                            onDismiss()
                            }
                        }
                    )
                    ThemeOptionsRowTheme(
                        label = if (currentLang == "fa") "سیستم" else "System",
                        selected = currentMode == ThemeMode.SYSTEM,
                        onClick = {
                            themeViewModel.setTheme(ThemeMode.SYSTEM)
                            scope.launch {
                            onDismiss()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ThemeOptionsRowTheme(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
            .clickable(onClick = onClick)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = label,
            fontFamily = VazirFamily,
            color = MaterialTheme.colorScheme.surface,
            fontSize = 16.sp
        )
    }
}