package com.example.landwapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.landwapp.duengung.Duengung
import com.example.landwapp.repository.DuengungRepository
import com.example.landwapp.data.model.DuengungProdukt
import kotlinx.coroutines.launch
import java.time.LocalDate

class DuengungViewModel(
    private val repository: DuengungRepository
) : ViewModel() {

    val produktliste = listOf(
        DuengungProdukt("Rindermist", "t/ha", 5.0, 3.0, 10.0),
        DuengungProdukt("Rindergülle", "m³/ha", 3.0, 1.5, 5.0),
        DuengungProdukt("Schweinegülle", "m³/ha", 4.5, 2.0, 3.5)
    )

    fun speichereDuengung(
        feldstueckId: Int,
        produktName: String,
        menge: Double,
        einheit: String
    ) {
        val produkt = produktliste.find { it.name == produktName } ?: return
        val heute = LocalDate.now().toString()

        val duengung = Duengung(
            feldstueckId = feldstueckId,
            produktname = produktName,
            mengeProHektar = menge,
            einheit = einheit,
            stickstoffN = menge * produkt.n,
            phosphorP = menge * produkt.p,
            kaliumK = menge * produkt.k,
            datum = heute
        )

        viewModelScope.launch {
            repository.insert(duengung)
        }
    }
}
