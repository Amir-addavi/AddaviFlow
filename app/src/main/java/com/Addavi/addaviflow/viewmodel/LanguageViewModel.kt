package com.Addavi.addaviflow.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Addavi.addaviflow.data.local.LanguageDataStore
import com.Addavi.addaviflow.util.LocaleHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class LanguageViewModel : ViewModel() {
    private val _language = MutableStateFlow("en")
    val language: StateFlow<String> = _language

    fun loadLanguage(context: Context) {
        viewModelScope.launch {
            val saved = LanguageDataStore.getLanguage(context)
            _language.value = saved ?: if (LocaleHelper.getDeviceLanguage() == "fa") "fa" else "en"
        }
    }

    fun changeLanguage(context: Context, langCode: String) {
        viewModelScope.launch {
            LanguageDataStore.saveLanguage(context, langCode)
            _language.value = langCode

            if (context is Activity) {
                context.recreate()
            }
        }
    }
}