package org.example.collectorsapp

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import androidx.navigation.navOptions
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable
import org.example.collectorsapp.data.CollectionDatabase
import org.example.collectorsapp.ui.components.NavBar
import org.example.collectorsapp.ui.components.topbars.AddEditTopBar
import org.example.collectorsapp.ui.components.topbars.DetailTopBar
import org.example.collectorsapp.ui.components.topbars.PickCollectionTopBar
import org.example.collectorsapp.ui.components.topbars.TitleOnlyTopBar
import org.example.collectorsapp.ui.theme.DarkColorScheme
import org.example.collectorsapp.ui.theme.LightColorScheme
import org.example.collectorsapp.ui.theme.rippleConfiguration
import org.example.collectorsapp.ui.views.ai.AiAssistView
import org.example.collectorsapp.ui.views.collections.AddEditCollectionView
import org.example.collectorsapp.ui.views.collections.CollectionDetailView
import org.example.collectorsapp.ui.views.collections.CollectionsPickView
import org.example.collectorsapp.ui.views.collections.CollectionsView
import org.example.collectorsapp.ui.views.items.AddEditItemView
import org.example.collectorsapp.ui.views.items.ItemDetailView
import org.example.collectorsapp.ui.views.settings.SettingsView
import org.example.collectorsapp.viewmodels.AiAssistViewModel
import org.example.collectorsapp.viewmodels.CollectionDetailViewModel
import org.example.collectorsapp.viewmodels.CollectionsListViewModel
import org.example.collectorsapp.viewmodels.ItemDetailsViewmodel
import org.example.collectorsapp.viewmodels.SettingsViewModel

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
                    .background(if (darkTheme) MaterialTheme.colorScheme.surfaceContainer else Color.White)
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
                        TopAppBarType.PickCollectionTopBar -> {
                            PickCollectionTopBar()
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
                val settingsViewModel = viewModel { SettingsViewModel(repository) }
                val aiAssistViewModel = viewModel { AiAssistViewModel(repository, "gemini-2.0-flash") }
                val settingsState by settingsViewModel.uiState.collectAsStateWithLifecycle()

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
                            },
                            currency = settingsState.currencyUI.name,
                            modifier = Modifier.padding(12.dp)
                        )
                        showBottomAppBar = true
                        topAppBarType = TopAppBarType.TitleOnly
                    }
                    composable<NavigationDestination.AiAssistView> {
                        AiAssistView(
                            viewModel = aiAssistViewModel,
                            onActionClick = {
                                navController.navigate(NavigationDestination.CollectionsPickView)
                            },
                            modifier = Modifier.padding(12.dp)
                        )
                        showBottomAppBar = true
                        topAppBarType = TopAppBarType.TitleOnly
                    }
                    composable<NavigationDestination.SettingsView> {
                        SettingsView(
                            viewModel = settingsViewModel,
                            modifier = Modifier.padding(8.dp)
                        )
                        showBottomAppBar = true
                        topAppBarType = TopAppBarType.TitleOnly
                    }

                    // Collections destinations
                    composable<NavigationDestination.AddEditCollectionView> {
                        val args = it.toRoute<NavigationDestination.AddEditCollectionView>()
                        AddEditCollectionView(
                            collectionId = args.collectionId,
                            viewModel = collectionsViewModel,
                            onBack = { navController.popBackStack() },
                            modifier = Modifier.padding(4.dp)
                        )
                        showBottomAppBar = false
                        topAppBarType = TopAppBarType.AddEdit
                    }
                    composable<NavigationDestination.CollectionDetailView> {
                        val args = it.toRoute<NavigationDestination.CollectionDetailView>()
                        val viewModel  = viewModel { CollectionDetailViewModel(args.collectionId, repository) }
                        CollectionDetailView(
                            viewModel = viewModel,
                            onAddClick = {
                                navController.navigate(NavigationDestination.AddEditItemView(args.collectionId))
                            },
                            onItemClick = { collectionId, itemId ->
                                navController.navigate(NavigationDestination.ItemDetailView(collectionId, itemId))
                            },
                            currency = settingsState.currencyUI.name,
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
                    composable<NavigationDestination.CollectionsPickView> {
                        CollectionsPickView(
                            viewModel = collectionsViewModel,
                            onCollectionClick = { collectionId ->
                                aiAssistViewModel.setCollectionId(collectionId)
                                navController.navigate(NavigationDestination.AiAssistView, navOptions  {
                                    popUpTo(0) {inclusive = true}
                                    launchSingleTop = true
                                }) },
                            currency = settingsState.currencyUI.name,
                            modifier = Modifier.padding(12.dp)
                        )
                        showBottomAppBar = false
                        topAppBarType = TopAppBarType.PickCollectionTopBar
                    }

                    // Items destinations
                    composable<NavigationDestination.AddEditItemView> {
                        val args = it.toRoute<NavigationDestination.AddEditItemView>()
                        val viewModel  = viewModel { CollectionDetailViewModel(args.collectionId, repository) }
                        AddEditItemView(
                            collectionId = args.collectionId,
                            itemId = args.itemId,
                            viewModel = viewModel,
                            onBack = { navController.popBackStack() },
                            modifier = Modifier.padding(4.dp)
                        )
                        showBottomAppBar = false
                        topAppBarType = TopAppBarType.AddEdit
                    }
                    composable<NavigationDestination.ItemDetailView> {
                        val args = it.toRoute<NavigationDestination.ItemDetailView>()
                        val viewModel = viewModel { ItemDetailsViewmodel(args.collectionId, args.itemId, repository) }
                        ItemDetailView(
                            viewModel = viewModel,
                            currency = settingsState.currencyUI.name,
                            modifier = Modifier.padding(4.dp)
                        )
                        deleteAction = {
                            viewModel.deleteItem()
                        }
                        editAction = {
                            navController.navigate(NavigationDestination.AddEditItemView(args.collectionId, args.itemId))
                        }
                        showBottomAppBar = false
                        topAppBarType = TopAppBarType.DetailTopBar
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
    object AiAssistView
    @Serializable
    object SettingsView
    @Serializable
    object CollectionsPickView
    @Serializable
    data class AddEditCollectionView(val collectionId: Long? = null)
    @Serializable
    data class CollectionDetailView(val collectionId: Long)
    @Serializable
    data class AddEditItemView(val collectionId: Long, val itemId: Long? = null)
    @Serializable
    data class ItemDetailView(val collectionId: Long, val itemId: Long)
}

enum class TopAppBarType {
    TitleOnly,
    AddEdit,
    DetailTopBar,
    PickCollectionTopBar
}