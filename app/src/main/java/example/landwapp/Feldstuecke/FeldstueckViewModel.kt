package com.example.landwapp.feldstuecke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.landwapp.data.model.Feldstueck

class FeldstueckViewModel(private val repo: FeldstueckRepository) : ViewModel() {

    val feldstuecke: StateFlow<List<Feldstueck>> =
        repo.alleFeldstuecke.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    suspend fun getFeldstueckById(id: Int): Feldstueck? = repo.getFeldstueckById(id)

    fun insert(feld: Feldstueck) {
        viewModelScope.launch { repo.insert(feld) }
    }

    fun update(feld: Feldstueck) {
        viewModelScope.launch { repo.update(feld) }
    }

    fun delete(feld: Feldstueck) {
        viewModelScope.launch { repo.delete(feld) }
    }
}
