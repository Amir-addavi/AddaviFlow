package com.Addavi.addaviflow

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.Addavi.addaviflow.core.navigation.SetupNavGraph
import com.Addavi.addaviflow.core.splash.SplashScreen
import com.Addavi.addaviflow.model.bottemNavItem
import com.Addavi.addaviflow.ui.components.BottomNavBar
import com.Addavi.addaviflow.ui.theme.AddaviFlowTheme
import com.Addavi.addaviflow.viewmodel.ThemeViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val themeViewModel :ThemeViewModel = viewModel()
            val currentTheme by themeViewModel.themeModeState.collectAsState()
            AddaviFlowTheme(themeMode = currentTheme) {
                    val navController = rememberNavController()
                var showSplash by remember { mutableStateOf(true) }

                if (showSplash) {
                    SplashScreen { showSplash = false }
                } else {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = colorResource(R.color.transparent)
                    )
                    {
                        Box(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            SetupNavGraph(
                                navController = navController,
                            )
                            BottomNavBar(
                                navController = navController,
                                items = bottemNavItem,
                                modifier = Modifier.align(Alignment.BottomCenter)
                                    .padding(bottom = 10.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}