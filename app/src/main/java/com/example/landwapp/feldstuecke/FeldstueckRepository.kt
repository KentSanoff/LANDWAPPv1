package com.example.landwapp.feldstuecke

import com.example.landwapp.data.model.Feldstueck
import com.example.landwapp.feldstuecke.FeldstueckDao
import kotlinx.coroutines.flow.Flow

class FeldstueckRepository(private val feldstueckDao: FeldstueckDao) {

    val alleFeldstuecke: Flow<List<Feldstueck>> = feldstueckDao.getAllFeldstuecke()

    suspend fun getAll(): List<Feldstueck> = feldstueckDao.getAllFeldstueckeList()

    suspend fun getFeldstueckById(id: Int): Feldstueck? {
        return feldstueckDao.getFeldstueckById(id)
    }

    suspend fun insert(feldstueck: Feldstueck) {
        feldstueckDao.insert(feldstueck)
    }

    suspend fun update(feldstueck: Feldstueck) {
        feldstueckDao.update(feldstueck)
    }

    suspend fun delete(feldstueck: Feldstueck) {
        feldstueckDao.delete(feldstueck)
    }

    suspend fun deleteAll() {
        feldstueckDao.deleteAll()
    }

    suspend fun insertiereMehrereFeldstuecke(feldstuecke: List<Feldstueck>) {
        feldstueckDao.insertiereMehrereFeldstuecke(feldstuecke)
    }

    suspend fun getKulturByFeldstueckId(id: Int): String? {
        return feldstueckDao.getKulturById(id)
    }

    suspend fun updateKulturByFeldstueckId(id: Int, newKultur: String) {
        feldstueckDao.updateKulturByFeldstueckId(id, newKultur)
    }
}
