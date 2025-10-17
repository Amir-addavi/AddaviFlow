package com.Addavi.addaviflow.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.Addavi.addaviflow.ui.theme.ThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATASTORE_NAME = "user_setting"
val Context.dataStore by preferencesDataStore(name = DATASTORE_NAME)

private val THEME_KEY = stringPreferencesKey("App_Theme_mode")

fun Context.themeModeFlow(): Flow<ThemeMode>{
    return this.dataStore.data.map {
        preferences ->
        val value = preferences[THEME_KEY] ?: ThemeMode.SYSTEM.name
        try {
            ThemeMode.valueOf(value)
        }catch (e: Exception){
            ThemeMode.SYSTEM
        }
    }
}

suspend fun Context.saveThemeMode(mode : ThemeMode){
    this.dataStore.edit { preferences -> preferences[THEME_KEY] = mode.name }
}