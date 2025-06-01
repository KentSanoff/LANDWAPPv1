package example.landwapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun FeldstueckDetailScreen(
    feldstueckId: Int,
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feldstück-Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Zurück")
                    }
                }
            )
        }
    ) { padding ->
        // TODO: Hole das Feldstück mit der Id aus deinem ViewModel/Repository
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text("Details zu Feldstück $feldstueckId")
            // Beispielinhalt:
            Text("Name: ...")
            Text("Kultur: ...")
            Text("Nutzung: ...")
            Text("Fläche: ... ha")
            // Hier kannst du bearbeiten/löschen usw. anbieten
        }
    }
}
