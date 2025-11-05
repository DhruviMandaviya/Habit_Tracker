package com.example.dhruvi.habittracker.presentation.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.dhruvi.habittracker.data.BottomNavItem
import com.example.dhruvi.habittracker.presentation.components.BottomNavigationBar


@Preview
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
                    },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Add new habit */ },
                containerColor = Color.Transparent,
                shape = CircleShape,
                modifier = Modifier
                    .size(60.dp)
                    .border(1.dp, Color(0xFF42E2C2), CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add",
                    tint = Color(0xFF42E2C2)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Habits.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Habits.route) { /* HabitsScreen() */ }
            composable(BottomNavItem.Progress.route) { /* ProgressScreen() */ }
            composable(BottomNavItem.AI.route) { /* AiAssistantScreen() */ }
            composable(BottomNavItem.Tasks.route) { /* TasksScreen() */ }
            composable(BottomNavItem.Settings.route) { /* SettingsScreen() */ }
        }
    }
}
