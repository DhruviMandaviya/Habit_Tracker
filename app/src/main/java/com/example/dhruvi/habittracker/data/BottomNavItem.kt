package com.example.dhruvi.habittracker.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Habits : BottomNavItem("habits", Icons.Default.List, "Habits")
    object Progress : BottomNavItem("progress", Icons.Default.PieChart, "Progress")
    object AI : BottomNavItem("ai_assistant", Icons.Default.AutoAwesome, "AI Assistant")
    object Tasks : BottomNavItem("tasks", Icons.Default.CheckCircle, "Tasks")
    object Settings : BottomNavItem("settings", Icons.Default.Settings, "Settings")
}