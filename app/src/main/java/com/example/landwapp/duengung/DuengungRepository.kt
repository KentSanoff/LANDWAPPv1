package com.example.landwapp.duengung

import kotlinx.coroutines.flow.Flow

class DuengungRepository(private val dao: DuengungDao) {

    // Alle Düngungen (z. B. für Übersicht oder Export)
    fun getAlleDuengungen(): Flow<List<Duengung>> =
        dao.getAlleDuengungen()

    // Düngungen nur für ein bestimmtes Feldstück
    fun getDuengungenForFeldstueck(feldstueckId: Int): Flow<List<Duengung>> =
        dao.getDuengungenForFeldstueck(feldstueckId)

    // Neue Düngung speichern
    suspend fun insert(duengung: Duengung) {
        dao.insert(duengung)
    }

    // Bestehende Düngung aktualisieren
    suspend fun update(duengung: Duengung) {
        dao.update(duengung)
    }

    // Einzelne Düngung löschen
    suspend fun delete(duengung: Duengung) {
        dao.delete(duengung)
    }

    // Alle Düngungen löschen (z. B. für Zurücksetzen)
    suspend fun deleteAll() {
        dao.deleteAll()
    }
}
