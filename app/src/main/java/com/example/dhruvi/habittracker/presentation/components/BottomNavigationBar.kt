package com.example.dhruvi.habittracker.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dhruvi.habittracker.data.BottomNavItem

@Preview
@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavItem.Habits,
        BottomNavItem.Progress,
        BottomNavItem.AI,
        BottomNavItem.Tasks,
        BottomNavItem.Settings
    )

    NavigationBar(
        containerColor = Color(0xFF262626),
        tonalElevation = 20.dp,
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomEnd = 20.dp, bottomStart = 20.dp))
    ) {
        val currentDestination = navController.currentBackStackEntryAsState().value?.destination

        items.forEach { item ->
            val isSelected = currentDestination?.route == item.route

            NavigationBarItem(
                modifier = Modifier.offset(y = 15.dp),
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        tint = if (isSelected) Color(0xFF42E2C2) else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = item.label,
                        color = if (isSelected) Color(0xFF42E2C2) else Color.Gray,
                        fontSize = 12.sp
                    )
                }
            )
        }
    }
}
