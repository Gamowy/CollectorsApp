package org.example.collectorsapp.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.button_gemini_title
import kotlinproject.composeapp.generated.resources.button_home_title
import kotlinproject.composeapp.generated.resources.button_settings_title
import kotlinproject.composeapp.generated.resources.diamond_outline
import kotlinproject.composeapp.generated.resources.gemini
import kotlinproject.composeapp.generated.resources.settings
import kotlinproject.composeapp.generated.resources.string_icon
import org.example.collectorsapp.NavigationDestination
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource


data class NavigationItem(val title: StringResource, val icon: DrawableResource, val route: Any, val routeString: String)

val navigationItems = listOf(
    NavigationItem(
        title = Res.string.button_home_title,
        icon = Res.drawable.diamond_outline,
        route = NavigationDestination.CollectionsView,
        routeString = "NavigationDestination.CollectionsView"
    ),
    NavigationItem(
        title = Res.string.button_gemini_title,
        icon = Res.drawable.gemini,
        route = NavigationDestination.AiAssistView,
        routeString = "NavigationDestination.AiAssistView"
    ),
    NavigationItem(
        title = Res.string.button_settings_title,
        icon = Res.drawable.settings,
        route = NavigationDestination.SettingsView,
        routeString = "NavigationDestination.SettingsView"

    ),
)

@Composable
fun NavBar(
    navController: NavController,
) {
    var selectedIndex by remember { mutableStateOf(getInitialIndex(navController))}
    NavigationBar(
        tonalElevation = 4.dp
    ) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                onClick = {
                    navController.navigate(item.route, navOptions = navOptions {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(0) {inclusive = true}
                    })
                    selectedIndex = index
                },
                label = { Text(stringResource(item.title)) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = "${item.title} ${stringResource(Res.string.string_icon)}",
                        tint = Color.Black,
                    )
                },
                selected = selectedIndex == index,
                colors = NavigationBarItemColors(
                    selectedIconColor = NavigationBarItemDefaults.colors().selectedIconColor,
                    selectedTextColor = NavigationBarItemDefaults.colors().selectedTextColor,
                    selectedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = NavigationBarItemDefaults.colors().unselectedIconColor,
                    unselectedTextColor = NavigationBarItemDefaults.colors().unselectedTextColor,
                    disabledIconColor = NavigationBarItemDefaults.colors().disabledIconColor,
                    disabledTextColor = NavigationBarItemDefaults.colors().disabledTextColor
                )
            )
        }
    }
}

private fun getInitialIndex(navController: NavController) : Int {
    val currentRoute = navController.currentDestination?.route
    if (currentRoute != null) {
        val index = navigationItems.indexOfFirst { "org.example.collectorsapp.${it.routeString}" == currentRoute }
        if (index != -1)
            return index
    }
    return 0
}