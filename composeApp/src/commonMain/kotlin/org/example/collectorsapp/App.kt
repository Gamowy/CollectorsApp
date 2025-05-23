package org.example.collectorsapp

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalRippleConfiguration
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import kotlinproject.composeapp.generated.resources.Res
import kotlinx.serialization.Serializable
import org.example.collectorsapp.components.NavBar
import org.example.collectorsapp.theme.DarkColorScheme
import org.example.collectorsapp.theme.LightColorScheme
import org.example.collectorsapp.theme.rippleConfiguration
import org.example.collectorsapp.views.CollectionsView
import org.example.collectorsapp.views.GeminiView
import org.example.collectorsapp.views.SettingsView
import org.jetbrains.compose.resources.stringResource
import kotlinproject.composeapp.generated.resources.app_name
import org.example.collectorsapp.views.AddCollectionView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val darkTheme = isSystemInDarkTheme()
    val ADD_COLLECTION_VIEW = "add_collection"
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    ) {
        CompositionLocalProvider(LocalRippleConfiguration provides rippleConfiguration) {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing),
                topBar = {
                    Text(
                        text = stringResource(Res.string.app_name),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth().padding(4.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                bottomBar = {
                    BottomAppBar {
                        NavBar(navController)
                    }
                },
            ) {
                val navGraph = navController.createGraph(startDestination = CollectionsView) {
                    composable<CollectionsView> {
                        CollectionsView(navController)
                    }
                    composable<GeminiView> {
                        GeminiView()
                    }
                    composable<SettingsView> {
                        SettingsView()
                    }
                    composable(ADD_COLLECTION_VIEW) {
                        AddCollectionView()
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
object CollectionsView
@Serializable
object GeminiView
@Serializable
object SettingsView