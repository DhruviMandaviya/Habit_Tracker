package com.example.dhruvi.habittracker.presentation.ui.theme
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.dhruvi.habittracker.presentation.AppThemeMode
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


// 🎨 Light Theme Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = light_primary,
    onPrimary = light_onPrimary,
    secondary = light_secondary,
    onSecondary = light_onSecondary,
    background = light_background,
    surface = light_surface,
    onSurface = light_onSurface,
    surfaceVariant = light_surfaceVariant,
    outline = light_outline,
    error = light_error,
    onError = light_onError
)

// 🌚 Dark Theme Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = dark_primary,
    onPrimary = dark_onPrimary,
    secondary = dark_secondary,
    onSecondary = dark_onSecondary,
    background = dark_background,
    surface = dark_surface,
    onSurface = dark_onSurface,
    surfaceVariant = dark_surfaceVariant,
    outline = dark_outline,
    error = dark_error,
    onError = dark_onError
)

@Composable
fun AppTheme(
    themeFlow: Flow<AppThemeMode>,
    content: @Composable () -> Unit
) {
    var themeMode by remember { mutableStateOf(AppThemeMode.SYSTEM) }

    LaunchedEffect(Unit) {
        themeFlow.collectLatest { themeMode = it }
    }

    val darkTheme = when (themeMode) {
        AppThemeMode.LIGHT -> false
        AppThemeMode.DARK -> true
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val colors = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        content = content
    )
}