package com.example.landwapp.feldstuecke

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.landwapp.data.model.Feldstueck

@Composable
fun FeldstueckDetailScreen(
    viewModel: FeldstueckViewModel = viewModel(),
    feldstueckId: Int? = null,
    onSaveFinished: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var kultur by remember { mutableStateOf("") }
    var nutzung by remember { mutableStateOf("") }
    var flaecheHa by remember { mutableStateOf("") }

    // Optional: laden bei bestehendem ID
    LaunchedEffect(feldstueckId) {
        feldstueckId?.let {
            val feldstueck = viewModel.getFeldstueckById(it)
            feldstueck?.let {
                name = it.name
                kultur = it.kultur
                nutzung = it.nutzung
                flaecheHa = it.flaecheHa.toString()
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (feldstueckId == null) "Neues Feldstück" else "Feldstück bearbeiten") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val feld = Feldstueck(
                    id = feldstueckId ?: 0,
                    name = name,
                    kultur = kultur,
                    nutzung = nutzung,
                    flaecheHa = flaecheHa.toDoubleOrNull() ?: 0.0
                )
                if (feldstueckId == null) {
                    viewModel.insert(feld)
                } else {
                    viewModel.update(feld)
                }
                onSaveFinished()
            }) {
                Text("💾")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = kultur, onValueChange = { kultur = it }, label = { Text("Kultur") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = nutzung, onValueChange = { nutzung = it }, label = { Text("Nutzung") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = flaecheHa, onValueChange = { flaecheHa = it }, label = { Text("Fläche (ha)") }, modifier = Modifier.fillMaxWidth())
        }
    }
}
