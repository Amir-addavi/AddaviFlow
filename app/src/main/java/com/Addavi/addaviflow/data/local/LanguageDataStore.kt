package com.Addavi.addaviflow.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore(name = "user_settings")
object LanguageDataStore {
    private val LANGUAGE_KEY = stringPreferencesKey("app_language")

    suspend fun saveLanguage(context: Context, languageCode: String) {
        context.dataStore.edit { preferences ->
            preferences[LANGUAGE_KEY] = languageCode
        }
    }
    suspend fun getLanguage(context: Context): String? {
        val pref = context.dataStore.data.first()
        return pref[LANGUAGE_KEY]
    }
}