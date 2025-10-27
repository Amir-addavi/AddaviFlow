package com.Addavi.addaviflow

import android.R
import android.R.attr.layoutDirection
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.Addavi.addaviflow.core.navigation.SetupNavGraph
import com.Addavi.addaviflow.core.splash.SplashScreen
import com.Addavi.addaviflow.model.bottemNavItem
import com.Addavi.addaviflow.ui.components.BottomNavBar
import com.Addavi.addaviflow.ui.theme.AddaviFlowTheme
import com.Addavi.addaviflow.util.LocaleHelper
import com.Addavi.addaviflow.viewmodel.FetchDataViewModel
import com.Addavi.addaviflow.viewmodel.LanguageViewModel
import com.Addavi.addaviflow.viewmodel.ThemeViewModel



class MainActivity : ComponentActivity() {
    private val languageViewModel: LanguageViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        languageViewModel.loadLanguage(this)
        enableEdgeToEdge()

        setContent {
            val lang by languageViewModel.language.collectAsState()
            val localizedContext = remember(lang) { LocaleHelper.setLocale(this, lang) }
            val layoutDirection = if (lang == "fa") LayoutDirection.Rtl else LayoutDirection.Ltr

            CompositionLocalProvider(
                LocalContext provides localizedContext,
                LocalLayoutDirection provides layoutDirection
            ) {
                val themeViewModel: ThemeViewModel = viewModel()
                val currentTheme by themeViewModel.themeModeState.collectAsState()
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                AddaviFlowTheme(themeMode = currentTheme) {
                    var showSplash by remember { mutableStateOf(true) }

                    if (showSplash) {
                        SplashScreen { showSplash = false }
                    } else {
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = colorResource(R.color.transparent)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                SetupNavGraph(
                                    languageViewModel = languageViewModel,
                                    navController = navController,
                                )
                                if (currentRoute != "info" && currentRoute != "webview/{url}") {
                                    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                                        BottomNavBar(
                                            navController = navController,
                                            items = bottemNavItem,
                                            modifier = Modifier
                                                .align(Alignment.BottomCenter)
                                                .padding(bottom = 10.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}