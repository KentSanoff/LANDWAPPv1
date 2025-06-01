package com.example.landwapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.landwapp.duengung.Duengung
import com.example.landwapp.duengung.DuengungProdukt
import java.time.LocalDate

@Composable
fun DuengungDetailScreen(
    vorhandeneDuengung: Duengung? = null,
    produktliste: List<DuengungProdukt>,
    onSpeichern: (Duengung) -> Unit
) {
    val istBearbeiten = vorhandeneDuengung != null

    var produktname by remember { mutableStateOf(vorhandeneDuengung?.produktname ?: "") }
    var menge by remember { mutableStateOf(vorhandeneDuengung?.mengeProHektar?.toString() ?: "") }
    val datum = vorhandeneDuengung?.datum ?: LocalDate.now().toString()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = if (istBearbeiten) "Düngung bearbeiten" else "Neue Düngung", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(12.dp))

        // Produkt Dropdown
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
            TextField(
                value = produktname,
                onValueChange = { produktname = it },
                label = { Text("Produkt") },
                modifier = Modifier.menuAnchor().fillMaxWidth()
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                produktliste.forEach {
                    DropdownMenuItem(
                        text = { Text(it.name) },
                        onClick = {
                            produktname = it.name
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextField(
            value = menge,
            onValueChange = { menge = it },
            label = { Text("Menge pro ha") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(onClick = {
            val mengeDouble = menge.toDoubleOrNull() ?: return@Button
            val produkt = produktliste.find { it.name == produktname } ?: return@Button

            val neueDuengung = Duengung(
                id = vorhandeneDuengung?.id ?: 0,
                feldstueckId = vorhandeneDuengung?.feldstueckId ?: 0,
                produktname = produktname,
                mengeProHektar = mengeDouble,
                einheit = produkt.einheit,
                stickstoffN = mengeDouble * produkt.nProEinheit,
                phosphorP = mengeDouble * produkt.pProEinheit,
                kaliumK = mengeDouble * produkt.kProEinheit,
                datum = datum
            )
            onSpeichern(neueDuengung)
        }) {
            Text("Speichern")
        }
    }
}
