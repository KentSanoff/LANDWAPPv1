package example.landwapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun PflanzenschutzDetailScreen(
    psId: Int,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pflanzenschutz-Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Zurück")
                    }
                }
            )
        }
    ) { padding ->
        // TODO: Hole den Pflanzenschutz-Eintrag aus ViewModel/Repository
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Details zum Pflanzenschutz $psId")
            // Beispielinhalt:
            Text("Mittel: ...")
            Text("Menge: ...")
            Text("Einheit: ...")
            Text("Datum: ...")
        }
    }
}
