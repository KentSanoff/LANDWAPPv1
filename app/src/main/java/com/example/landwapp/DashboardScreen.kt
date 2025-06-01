package com.example.landwapp

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DashboardScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = androidx.compose.ui.Modifier.padding(paddingValues)
        ) {
            Text(
                text = "Willkommen im Dashboard!",
                style = MaterialTheme.typography.titleLarge,
                modifier = androidx.compose.ui.Modifier.padding(24.dp)
            )
        }
    }
}
