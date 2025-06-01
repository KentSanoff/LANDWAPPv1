package com.example.landwapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.landwapp.ui.screens.DashboardScreen
import com.example.landwapp.ui.screens.FeldstueckeScreen
import com.example.landwapp.ui.screens.DuengungScreen
import com.example.landwapp.ui.screens.SettingsScreen
// Importiere hier alle weiteren Screens, die du nutzt!

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(navController = navController)
        }
        composable("feldstuecke") {
            FeldstueckeScreen(navController = navController)
        }
        composable("duengung") {
            DuengungScreen(navController = navController)
        }
        composable("settings") {
            SettingsScreen(navController = navController)
        }

        // Hier der Detail-Screen mit Parameter:
        composable("details/{itemId}") { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")
            DetailScreen(itemId = itemId, navController = navController)
        }
    }
}
