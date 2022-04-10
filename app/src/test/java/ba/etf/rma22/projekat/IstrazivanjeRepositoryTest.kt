package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import org.junit.Test

import org.junit.Assert.*

class IstrazivanjeRepositoryTest {
    @Test
    fun getIstrazivanjeByGodinaTest() {
        val istrazivanje: Istrazivanje = Istrazivanje("Istrazivanje A", 1)
        assertEquals(listOf(istrazivanje), IstrazivanjeRepository.getIstrazivanjeByGodina(1))
    }
    @Test
    fun getUpisaniTest(){
        val istrazivanje = Istrazivanje("Istrazivanje A",1)
        val istrazivanje2 = Istrazivanje("Istrazivanje C",3)
        IstrazivanjeRepository.dodajUpisani(istrazivanje)

        assertEquals(listOf(istrazivanje2,istrazivanje),IstrazivanjeRepository.getUpisani())
    }
}