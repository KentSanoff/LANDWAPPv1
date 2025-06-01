package com.example.landwapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.landwapp.duengung.Duengung
import com.example.landwapp.duengung.Grenzwerte

@Composable
fun DuengungListScreen(
    duengungen: List<Duengung>,
    feldstueckMap: Map<Int, String>,
    onBearbeiten: (Duengung) -> Unit,
    onLoeschen: (Duengung) -> Unit
) {
    var zuLoeschendeDuengung by remember { mutableStateOf<Duengung?>(null) }

    fun istGrenzwertUeberschritten(duengung: Duengung, kultur: String): Boolean {
        val g = Grenzwerte.werte[kultur] ?: return false
        return duengung.stickstoffN > g.n || duengung.phosphorP > g.p || duengung.kaliumK > g.k
    }

    LazyColumn {
        items(duengungen) { duengung ->
            val kultur = feldstueckMap[duengung.feldstueckId] ?: "Unbekannt"
            val istUeberGrenze = istGrenzwertUeberschritten(duengung, kultur)

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (istUeberGrenze) Color(0xFFFFCDD2) else Color(0xFFC8E6C9)
                )
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text("Produkt: ${duengung.produktname}")
                    Text("Menge: ${duengung.mengeProHektar} ${duengung.einheit}")
                    Text("N: ${duengung.stickstoffN} kg/ha")
                    Text("P: ${duengung.phosphorP} kg/ha")
                    Text("K: ${duengung.kaliumK} kg/ha")
                    Text("Datum: ${duengung.datum}")
                    Text("Kultur: $kultur", fontStyle = FontStyle.Italic)

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        Button(onClick = { onBearbeiten(duengung) }) {
                            Text("Bearbeiten")
                        }
                        Button(
                            onClick = { zuLoeschendeDuengung = duengung },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                        ) {
                            Text("Löschen", color = Color.White)
                        }
                    }
                }
            }
        }
    }

    // Bestätigungsdialog
    if (zuLoeschendeDuengung != null) {
        AlertDialog(
            onDismissRequest = { zuLoeschendeDuengung = null },
            title = { Text("Eintrag löschen") },
            text = { Text("Möchtest du diesen Düngungseintrag wirklich löschen?") },
            confirmButton = {
                TextButton(onClick = {
                    zuLoeschendeDuengung?.let { onLoeschen(it) }
                    zuLoeschendeDuengung = null
                }) {
                    Text("Ja")
                }
            },
            dismissButton = {
                TextButton(onClick = { zuLoeschendeDuengung = null }) {
                    Text("Abbrechen")
                }
            }
        )
    }
}
