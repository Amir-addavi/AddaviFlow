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
import com.Addavi.addaviflow.R
import com.Addavi.addaviflow.ui.theme.VazirFamily
import com.Addavi.addaviflow.util.LocaleHelper
import com.Addavi.addaviflow.viewmodel.LanguageViewModel
import kotlinx.coroutines.launch

@Composable
fun DialogLang(
    onDismiss: () -> Unit,
    languageViewModel: LanguageViewModel
) {
    val scope = rememberCoroutineScope()

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
                        text = if (currentLang == "fa") "انتخاب زبان" else "Select Language",
                        fontFamily = VazirFamily,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.surface
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    ThemeOptionsRowLang(
                        label = stringResource(R.string.lang_en),
                        selected = currentLang == "en",
                        onClick = {
                            scope.launch {
                                languageViewModel.changeLanguage(baseContext, "en")
                                onDismiss()
                            }
                        }
                    )

                    ThemeOptionsRowLang(
                        label = "فارسی",
                        selected = currentLang == "fa",
                        onClick = {
                            scope.launch {
                                languageViewModel.changeLanguage(baseContext, "fa")
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
    fun ThemeOptionsRowLang(label: String, selected: Boolean, onClick: () -> Unit) {
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