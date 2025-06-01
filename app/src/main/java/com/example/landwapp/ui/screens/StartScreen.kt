package com.example.landwapp.ui.screens

import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.landwapp.duengung.DuengungViewModel
import com.example.landwapp.util.BackupUtils
import kotlinx.coroutines.launch

@Composable
fun StartScreen(viewModel: DuengungViewModel) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    // SAF-Launcher zum Erstellen einer Datei
    val backupLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CreateDocument("application/octet-stream"),
        onResult = { uri ->
            if (uri != null) {
                scope.launch {
                    val success = BackupUtils.backupDatabase(context, uri)
                    Toast.makeText(
                        context,
                        if (success) "✅ Backup erfolgreich!" else "❌ Backup fehlgeschlagen.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("LandwirtschaftsApp – Start") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Willkommen!", style = MaterialTheme.typography.titleLarge)

            Button(onClick = {
                backupLauncher.launch("landwapp-backup.db")
            }) {
                Text("📤 Backup zu Google Drive")
            }

            // Weitere Buttons oder Infos hier ergänzen…
        }
    }
}
