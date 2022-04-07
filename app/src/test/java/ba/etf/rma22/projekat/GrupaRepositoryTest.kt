package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import org.junit.Test

import org.junit.Assert.*

class GrupaRepositoryTest {
    @Test
    fun getGroupsByIstrazivanjeTest() {
        val istrazivanje: Istrazivanje = Istrazivanje("Istrazivanje A", 1)
        val grupa1: Grupa = Grupa("Grupa 1a","Istrazivanje A")
        val grupa2: Grupa = Grupa("Grupa 2a","Istrazivanje A")


        assertEquals(listOf(grupa1,grupa2), GrupaRepository.getGroupsByIstrazivanje(istrazivanje.naziv))
    }
}