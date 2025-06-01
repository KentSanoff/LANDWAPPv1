package com.example.landwapp.duengung

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun DuengungScreen(viewModel: DuengungViewModel = viewModel()) {
    val alleDuengungen by viewModel.alleDuengungen.collectAsState()

    var feldstueckId by remember { mutableStateOf("") }
    var produkt by remember { mutableStateOf("") }
    var menge by remember { mutableStateOf("") }
    var einheit by remember { mutableStateOf("kg") }
    var n by remember { mutableStateOf("") }
    var p by remember { mutableStateOf("") }
    var k by remember { mutableStateOf("") }
    var datum by remember { mutableStateOf("") }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Düngung") }) }
    ) { padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(16.dp)
        ) {
            OutlinedTextField(value = feldstueckId, onValueChange = { feldstueckId = it }, label = { Text("Feldstück-ID") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = produkt, onValueChange = { produkt = it }, label = { Text("Produktname") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = menge, onValueChange = { menge = it }, label = { Text("Menge/ha") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = einheit, onValueChange = { einheit = it }, label = { Text("Einheit (kg/m³)") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = n, onValueChange = { n = it }, label = { Text("N (kg/ha)") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = p, onValueChange = { p = it }, label = { Text("P₂O₅ (kg/ha)") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = k, onValueChange = { k = it }, label = { Text("K₂O (kg/ha)") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = datum, onValueChange = { datum = it }, label = { Text("Datum (YYYY-MM-DD)") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = {
                val duengung = Duengung(
                    feldstueckId = feldstueckId.toIntOrNull() ?: 0,
                    produktname = produkt,
                    mengeProHektar = menge.toDoubleOrNull() ?: 0.0,
                    einheit = einheit,
                    stickstoffN = n.toDoubleOrNull() ?: 0.0,
                    phosphorP = p.toDoubleOrNull() ?: 0.0,
                    kaliumK = k.toDoubleOrNull() ?: 0.0,
                    datum = datum
                )
                viewModel.insert(duengung)
                // Felder leeren nach dem Speichern
                feldstueckId = ""
                produkt = ""
                menge = ""
                einheit = "kg"
                n = ""
                p = ""
                k = ""
                datum = ""
            }) {
                Text("Speichern")
            }

            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn {
                items(alleDuengungen) { d ->
                    Text("• ${d.produktname} auf ID ${d.feldstueckId} – ${d.mengeProHektar} ${d.einheit} am ${d.datum}")
                }
            }
        }
    }
}
