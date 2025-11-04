package com.example.dhruvi.habittracker.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.dhruvi.habittracker.presentation.ThemeManager
import com.example.dhruvi.habittracker.presentation.ui.components.AnimatedThemeToggle

@Composable
fun ThemeSettingsScreen(themeManager: ThemeManager) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Theme Mode", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))
        AnimatedThemeToggle(themeManager)
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Tap to switch between Light and Dark mode",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
        )
    }
}
