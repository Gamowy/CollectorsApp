package org.example.collectorsapp.ui.theme

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RippleConfiguration
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val LightPrimary = Color(0xFF66CF85)
val LightOnPrimary = Color(0xFFFFFFFF)
val LightPrimaryContainer = Color(0xFFDFF7E8)
val LightOnPrimaryContainer = Color(0xFF1B3C2B)
val LightSecondary = Color(0xFF4CAF7A)
val LightOnSecondary = Color(0xFFFFFFFF)
val LightBackground = Color(0xFFFFFFFF)
val LightOnBackground = Color(0xFF1C1B1F)
val LightSurface = Color(0xFFF8F8F8)
val LightOnSurface = Color(0xFF1C1B1F)
val LightError = Color(0xFFB3261E)
val LightOnError = Color(0xFFFFFFFF)
val lightSurfaceColor = Color(0xFFD3D3D3)

val DarkPrimary = Color(0xFF66CF85)
val DarkOnPrimary = Color(0xFF00391C)
val DarkPrimaryContainer = Color(0xFF1B3C2B)
val DarkOnPrimaryContainer = Color(0xFFDFF7E8)
val DarkSecondary = Color(0xFF4CAF7A)
val DarkOnSecondary = Color(0xFF00391C)
val DarkBackground = Color(0xFF222222)
val DarkOnBackground = Color(0xFFE6E1E5)
val DarkSurface = Color(0xFF222222)
val DarkOnSurface = Color(0xFFE6E1E5)
val DarkError = Color(0xFFF2B8B5)
val DarkOnError = Color(0xFF601410)
val darkSurfaceColor = Color(0xFF2b2d30)

val LightColorScheme = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    background = LightBackground,
    onBackground = LightOnBackground,
    surface = LightSurface,
    onSurface = LightOnSurface,
    error = LightError,
    onError = LightOnError,
    surfaceContainer = lightSurfaceColor
)

val DarkColorScheme = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    error = DarkError,
    onError = DarkOnError,
    surfaceContainer = darkSurfaceColor
)

@OptIn(ExperimentalMaterial3Api::class)
val rippleConfiguration = RippleConfiguration(color = Color.Gray)