package org.example.collectorsapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import kotlinx.serialization.Serializable
import org.example.collectorsapp.components.NavBar
import org.example.collectorsapp.components.topbars.AddEditTopBar
import org.example.collectorsapp.theme.DarkColorScheme
import org.example.collectorsapp.theme.LightColorScheme
import org.example.collectorsapp.theme.rippleConfiguration
import org.example.collectorsapp.views.CollectionsView
import org.example.collectorsapp.views.GeminiView
import org.example.collectorsapp.views.SettingsView
import org.example.collectorsapp.components.topbars.TitleOnlyTopBar
import org.example.collectorsapp.views.AddCollectionView
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val darkTheme = isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    ) {
        CompositionLocalProvider(LocalRippleConfiguration provides rippleConfiguration) {
            val navController = rememberNavController()
            var showBottomAppBar by remember { mutableStateOf(true) }
            var topAppBarType by remember { mutableStateOf(TopAppBarType.TitleOnly) }

            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing),
                topBar = {
                    when (topAppBarType) {
                        TopAppBarType.TitleOnly -> {
                            TitleOnlyTopBar()
                        }
                        TopAppBarType.AddEdit -> {
                            AddEditTopBar(navController)
                        }
                    }
                },
                bottomBar = { if (showBottomAppBar) {
                    BottomAppBar {
                        NavBar(navController)
                    }
                } },
            ) {
                val navGraph = navController.createGraph(startDestination = NavigationDestination.CollectionsView) {
                    composable<NavigationDestination.CollectionsView> {
                        CollectionsView(navController)
                        showBottomAppBar = true
                        topAppBarType = TopAppBarType.TitleOnly
                    }
                    composable<NavigationDestination.GeminiView> {
                        GeminiView()
                        showBottomAppBar = true
                        topAppBarType = TopAppBarType.TitleOnly
                    }
                    composable<NavigationDestination.SettingsView> {
                        SettingsView()
                        showBottomAppBar = true
                        topAppBarType = TopAppBarType.TitleOnly
                    }
                    composable<NavigationDestination.AddCollectionView> {
                        AddCollectionView(navController)
                        showBottomAppBar = false
                        topAppBarType = TopAppBarType.AddEdit
                    }
                }
                NavHost(
                    navController = navController,
                    graph = navGraph,
                    modifier = Modifier.padding(it)
                )
            }
        }
    }
}

@Serializable
object NavigationDestination {
    @Serializable
    object CollectionsView
    @Serializable
    object GeminiView
    @Serializable
    object SettingsView
    @Serializable
    object AddCollectionView
}

enum class TopAppBarType {
    TitleOnly,
    AddEdit
}