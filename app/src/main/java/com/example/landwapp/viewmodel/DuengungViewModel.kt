package com.example.landwapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.landwapp.duengung.Duengung
import com.example.landwapp.duengung.DuengungProdukt
import com.example.landwapp.duengung.DuengungRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDate

class DuengungViewModel(private val repository: DuengungRepository) : ViewModel() {

    val alleDuengungen: StateFlow<List<Duengung>> = repository.getAlleDuengungen()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val produktliste = listOf(
        DuengungProdukt("Rindermist", "t/ha", 5.0, 3.0, 10.0),
        DuengungProdukt("Rindergülle", "m³/ha", 3.0, 1.5, 5.0),
        DuengungProdukt("Schweinegülle", "m³/ha", 4.5, 2.0, 3.5)
    )

    private val _ausgewaehlteDuengung = MutableStateFlow<Duengung?>(null)
    val ausgewaehlteDuengung: StateFlow<Duengung?> = _ausgewaehlteDuengung.asStateFlow()

    fun waehleDuengung(duengung: Duengung?) {
        _ausgewaehlteDuengung.value = duengung
    }

    fun speichereOderAktualisiereDuengung(
        name: String,
        menge: Double,
        feldstueckId: Int,
        datum: String = LocalDate.now().toString()
    ) {
        val produkt = produktliste.find { it.name == name } ?: return

        val neueDuengung = Duengung(
            id = _ausgewaehlteDuengung.value?.id ?: 0,
            feldstueckId = feldstueckId,
            produktname = name,
            mengeProHektar = menge,
            einheit = produkt.einheit,
            stickstoffN = menge * produkt.nProEinheit,
            phosphorP = menge * produkt.pProEinheit,
            kaliumK = menge * produkt.kProEinheit,
            datum = datum
        )

        viewModelScope.launch {
            if (_ausgewaehlteDuengung.value == null) {
                repository.insert(neueDuengung)
            } else {
                repository.update(neueDuengung)
            }
            _ausgewaehlteDuengung.value = null
        }
    }

    fun deleteDuengung(duengung: Duengung) {
        viewModelScope.launch {
            repository.delete(duengung)
        }
    }

    fun resetAuswahl() {
        _ausgewaehlteDuengung.value = null
    }
}
