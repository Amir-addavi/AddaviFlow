package com.Addavi.addaviflow.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.Addavi.addaviflow.data.saveThemeMode
import com.Addavi.addaviflow.data.themeModeFlow
import com.Addavi.addaviflow.ui.theme.ThemeMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ThemeViewModel(application: Application): AndroidViewModel(application){
    private val appContext = application.applicationContext

    val themeModeState : StateFlow<ThemeMode> = appContext.themeModeFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = ThemeMode.SYSTEM
    )

    fun setTheme(mode: ThemeMode){
        viewModelScope.launch {
            appContext.saveThemeMode(mode)
        }
    }
}