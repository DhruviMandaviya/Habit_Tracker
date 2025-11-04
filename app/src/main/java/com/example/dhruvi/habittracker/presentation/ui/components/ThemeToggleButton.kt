package com.example.dhruvi.habittracker.presentation.ui.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.example.dhruvi.habittracker.presentation.AppThemeMode
import com.example.dhruvi.habittracker.presentation.ThemeManager
import kotlinx.coroutines.launch

@Composable
fun AnimatedThemeToggle(
    themeManager: ThemeManager,
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    var currentTheme by remember { mutableStateOf(AppThemeMode.SYSTEM) }

    LaunchedEffect(Unit) {
        themeManager.themeFlow().collect { theme -> currentTheme = theme }
    }

    val isDark = when (currentTheme) {
        AppThemeMode.DARK -> true
        AppThemeMode.LIGHT -> false
        AppThemeMode.SYSTEM -> isSystemInDarkTheme()
    }

    val thumbOffset by animateDpAsState(
        targetValue = if (isDark) 36.dp else 0.dp,
        animationSpec = tween(400, easing = FastOutSlowInEasing)
    )

    val rotation by animateFloatAsState(
        targetValue = if (isDark) 360f else 0f,
        animationSpec = tween(600, easing = FastOutLinearInEasing)
    )

    Surface(
        modifier = modifier
            .clip(CircleShape)
            .clickable {
                val newTheme = if (isDark) AppThemeMode.LIGHT else AppThemeMode.DARK
                scope.launch { themeManager.setTheme(newTheme) }
            },
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .size(width = 72.dp, height = 36.dp)
                .background(
                    brush = if (isDark)
                        Brush.horizontalGradient(
                            listOf(Color(0xFF333232), Color(0xFF111111))
                        )
                    else Brush.horizontalGradient(
                        listOf(Color(0xFFFFD54F), Color(0xFFFFF176))
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            Box(
                modifier = Modifier
                    .offset(x = thumbOffset)
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(
                        color = if (isDark)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.primary
                    ),
                contentAlignment = Alignment.Center
            ) {
                Crossfade(targetState = isDark, animationSpec = tween(600)) { dark ->
                    if (dark) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Dark",
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .graphicsLayer(rotationZ = rotation)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Light",
                            tint = Color.White,
                            modifier = Modifier
                                .size(20.dp)
                                .graphicsLayer(rotationZ = rotation)
                        )
                    }
                }
            }
        }
    }
}
