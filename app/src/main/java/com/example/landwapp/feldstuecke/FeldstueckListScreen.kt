package com.example.landwapp.feldstuecke

import com.example.landwapp.data.model.Feldstueck
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel



@Composable
fun FeldstueckListScreen(
    viewModel: FeldstueckViewModel = viewModel(),
    onAddNew: () -> Unit = {},
    onEdit: (Int) -> Unit = {},
    onOpenDuengung: () -> Unit = {} // 🆕 Navigation zur Düngung
) {
    val feldstuecke = viewModel.feldstuecke.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Feldstücke") },
                actions = {
                    Button(onClick = onOpenDuengung) {
                        Text("Düngung")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onAddNew() }) {
                Text("+")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            contentPadding = innerPadding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(feldstuecke.value) { feldstueck ->
                FeldstueckItem(feldstueck, onClick = { onEdit(feldstueck.id) })
            }
        }
    }
}
