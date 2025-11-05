package com.example.dhruvi.habittracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.dhruvi.habittracker.presentation.ThemeManager
import com.example.dhruvi.habittracker.presentation.screens.MainScreen
import com.example.dhruvi.habittracker.presentation.ui.screens.ThemeSettingsScreen
import com.example.dhruvi.habittracker.presentation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    private lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeManager = ThemeManager(applicationContext)

        setContent {
            AppTheme(themeManager.themeFlow()) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                )
                {
//                    ThemeSettingsScreen(themeManager)
////                    AddTaskScreen()
                    val navController = rememberNavController()
                    MainScreen(navController)
                }

            }
        }
    }
}
