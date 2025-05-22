package org.example.collectorsapp.components

import androidx.compose.foundation.content.MediaType.Companion.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.gemini
import kotlinproject.composeapp.generated.resources.home
import kotlinproject.composeapp.generated.resources.settings
import kotlinx.coroutines.flow.collectLatest
import org.example.collectorsapp.CollectionsView
import org.example.collectorsapp.GeminiView
import org.example.collectorsapp.SettingsView
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class NavigationItem(val title: String, val icon: DrawableResource, val route: Any)

val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Res.drawable.home,
        route = CollectionsView
    ),
    NavigationItem(
        title = "Gemini",
        icon = Res.drawable.gemini,
        route = GeminiView
    ),
    NavigationItem(
        title = "Settings",
        icon = Res.drawable.settings,
        route = SettingsView
    ),
)


@Composable
fun NavBar(
    navController: NavController
) {
    var selectedIndex by remember { mutableStateOf(0)}
    NavigationBar {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                onClick = {
                    navController.navigate(item.route)
                    selectedIndex = index
                },
                label = { Text(item.title) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = "${item.title} Icon",
                        tint = Color.Black,
                    )
                },
                selected = selectedIndex == index
            )
        }
    }
    navController.addOnDestinationChangedListener { _, destination, _ ->
        val currentRoute = destination.route
        val currentIndex = navigationItems.indexOfFirst { it.route == currentRoute }
        selectedIndex = if (currentIndex != -1) {
            currentIndex
        } else {
            0
        }
    }
}