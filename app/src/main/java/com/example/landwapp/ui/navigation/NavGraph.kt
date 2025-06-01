package com.example.landwapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.landwapp.ui.screens.*

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start") {
            StartScreen(
                onFeldstueckeClick = { navController.navigate("feldstuecke") },
                onDuengungClick = { navController.navigate("duengung") }
            )
        }

        composable("feldstuecke") {
            FeldstueckListScreen()
        }

        composable("duengung") {
            DuengungListScreen(
                duengungen = listOf(), // Platzhalterdaten, später ViewModel
                onItemClick = { /* später Detailansicht */ }
            )
        }

        composable("duengung/detail") {
            DuengungDetailScreen(
                onSave = { /* später speichern + Navigation zurück */ }
            )

            composable("duengungListe/{feldstueckId}") { backStackEntry ->
                val feldstueckId = backStackEntry.arguments?.getString("feldstueckId")?.toIntOrNull() ?: 0
                DuengungListScreen(feldstueckId, viewModel = hiltViewModel())
            }

        }
    }
}
