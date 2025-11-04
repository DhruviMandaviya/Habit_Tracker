package com.example.dhruvi.habittracker.presentation

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore("theme_prefs")

enum class AppThemeMode { LIGHT, DARK, SYSTEM }

class ThemeManager(private val context: Context) {
    companion object {
        private val THEME_KEY = stringPreferencesKey("app_theme")
    }

    suspend fun setTheme(theme: AppThemeMode) {
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = theme.name
        }
    }

    fun themeFlow(): Flow<AppThemeMode> {
        return context.dataStore.data.map { prefs ->
            val themeName = prefs[THEME_KEY] ?: AppThemeMode.SYSTEM.name
            AppThemeMode.valueOf(themeName)
        }
    }
}