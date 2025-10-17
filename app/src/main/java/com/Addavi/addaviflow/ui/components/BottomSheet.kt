package com.Addavi.addaviflow.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.*
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.Addavi.addaviflow.ui.theme.ThemeMode
import com.Addavi.addaviflow.viewmodel.ThemeViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemePickerBottomSheet(
    sheetVisible: Boolean,
    onDimiss: () -> Unit,
    themeViewModel: ThemeViewModel = viewModel()
) {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    val currentMode by themeViewModel.themeModeState.collectAsState()

    if (sheetVisible) {
        ModalBottomSheet(
            containerColor = MaterialTheme.colorScheme.background,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                    onDimiss()
                }
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 17.dp)
                    .padding(bottom = 5.dp)
            ) {
                Text(
                    text = "Select Theme",
                    fontSize = 22.sp,
                    color = MaterialTheme.colorScheme.surface
                )
                Spacer(modifier = Modifier.height(15.dp))
                ThemeOptionsRow(
                    label = "Light",
                    selected = currentMode == ThemeMode.LIGHT,
                    onClick = {
                        themeViewModel.setTheme(ThemeMode.LIGHT)
                        scope.launch {
                            sheetState.hide()
                            onDimiss()
                        }
                    }
                )
                ThemeOptionsRow(
                    label = "Dark",
                    selected = currentMode == ThemeMode.DARK,
                    onClick = {
                        themeViewModel.setTheme(ThemeMode.DARK)
                        scope.launch {
                            sheetState.hide()
                            onDimiss()
                        }
                    }
                )
                ThemeOptionsRow(
                    label = "System",
                    selected = currentMode == ThemeMode.SYSTEM,
                    onClick = {
                        themeViewModel.setTheme(ThemeMode.SYSTEM)
                        scope.launch {
                            sheetState.hide()
                            onDimiss()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ThemeOptionsRow(label: String, selected: Boolean, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 5.dp)
        .clickable(onClick = onClick)) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(
            text = label
        )
    }
}