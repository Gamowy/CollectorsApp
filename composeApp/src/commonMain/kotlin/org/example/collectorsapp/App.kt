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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.ui.components.NavBar
import org.example.collectorsapp.ui.components.topbars.AddEditTopBar
import org.example.collectorsapp.ui.components.topbars.DetailTopBar
import org.example.collectorsapp.ui.components.topbars.TitleOnlyTopBar
import org.example.collectorsapp.ui.theme.DarkColorScheme
import org.example.collectorsapp.ui.theme.LightColorScheme
import org.example.collectorsapp.ui.theme.rippleConfiguration
import org.example.collectorsapp.ui.views.collections.AddEditCollectionView
import org.example.collectorsapp.ui.views.CollectionDetailView
import org.example.collectorsapp.ui.views.collections.CollectionsView
import org.example.collectorsapp.ui.views.collections.CollectionsListViewModel
import org.example.collectorsapp.ui.views.gemini.GeminiView
import org.example.collectorsapp.ui.views.settings.SettingsView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App(repository: CollectionDatabase) {
    val darkTheme = isSystemInDarkTheme()
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    ) {
        CompositionLocalProvider(LocalRippleConfiguration provides rippleConfiguration) {
            val navController = rememberNavController()
            var showBottomAppBar by remember { mutableStateOf(true) }

            var topAppBarType by remember { mutableStateOf(TopAppBarType.TitleOnly) }
            var editAction by remember { mutableStateOf({})}
            var deleteAction by remember { mutableStateOf({})}
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
                        TopAppBarType.DetailTopBar -> {
                            DetailTopBar(
                                onBack = { navController.popBackStack() },
                                onEdit = editAction,
                                onDelete = deleteAction
                            )
                        }
                    }
                },
                bottomBar = { if (showBottomAppBar) {
                    BottomAppBar {
                        NavBar(navController)
                    }
                } },
            ) { it ->
                val collectionsViewModel = viewModel { CollectionsListViewModel(repository) }

                val navGraph = navController.createGraph(startDestination = NavigationDestination.CollectionsView) {
                    // Bottom navigation destinations
                    composable<NavigationDestination.CollectionsView> {
                        CollectionsView(
                            viewModel = collectionsViewModel,
                            onCollectionClick = { id ->
                                navController.navigate(NavigationDestination.CollectionDetailView(id))
                            },
                            onAddClick = {
                                navController.navigate(NavigationDestination.AddEditCollectionView())
                            }
                        )
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

                    // Collections destinations
                    composable<NavigationDestination.AddEditCollectionView> {
                        val args = it.toRoute<NavigationDestination.AddEditCollectionView>()
                        AddEditCollectionView(
                            collectionId = args.collectionId,
                            viewModel = collectionsViewModel,
                            onBack = { navController.popBackStack() }
                        )
                        showBottomAppBar = false
                        topAppBarType = TopAppBarType.AddEdit
                    }
                    composable<NavigationDestination.CollectionDetailView> {
                        val args = it.toRoute<NavigationDestination.CollectionDetailView>()
                        CollectionDetailView(
                            collectionId = args.collectionId,
                            viewModel = collectionsViewModel,
                            onBack = { navController.popBackStack() },
                        )
                        showBottomAppBar = true
                        topAppBarType = TopAppBarType.DetailTopBar
                        deleteAction = {
                            collectionsViewModel.deleteCollectionById(args.collectionId)
                        }
                        editAction = {
                            navController.navigate(NavigationDestination.AddEditCollectionView(args.collectionId))
                        }
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
    data class AddEditCollectionView(val collectionId: Long? = null)
    @Serializable
    data class CollectionDetailView(val collectionId: Long)
}

enum class TopAppBarType {
    TitleOnly,
    AddEdit,
    DetailTopBar
}