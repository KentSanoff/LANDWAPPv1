package example.landwapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun DuengungDetailScreen(
    duengungId: Int,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Düngung-Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Zurück")
                    }
                }
            )
        }
    ) { padding ->
        // TODO: Hole die Düngung mit der Id aus deinem ViewModel/Repository
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Details zur Düngung $duengungId")
            // Beispielinhalt:
            Text("Produkt: ...")
            Text("Menge: ...")
            Text("Einheit: ...")
            Text("Datum: ...")
            // Bearbeiten/Löschen usw.
        }
    }
}
