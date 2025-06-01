package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ui.screens.*

@Composable
fun MainNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "dashboard") {
        composable("dashboard") { DashboardScreen() }
        composable("fields") { FieldsScreen() }
        composable("fieldDetail/{fieldId}") { backStackEntry ->
            FieldDetailScreen(fieldId = backStackEntry.arguments?.getString("fieldId"))
        }
        composable("dues") { DuesScreen() }
        composable("plantProtection") { PlantProtectionScreen() }
        composable("actions") { ActionsScreen() }
        composable("balance") { BalanceScreen() }
        composable("costs") { CostsScreen() }
        composable("inventory") { InventoryScreen() }
        composable("comparison") { ComparisonScreen() }
        composable("export") { ExportScreen() }
        composable("backup") { BackupScreen() }
        composable("settings") { SettingsScreen() }
    }
}
