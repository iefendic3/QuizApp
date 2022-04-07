package ba.etf.rma22.projekat

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository
import org.junit.Test

import org.junit.Assert.*
import java.util.*

class AnketaRepositoryTest {
    @Test
    fun getMyAnketeTest(){
        var cal: Calendar = Calendar.getInstance()
        cal.set(2022,4,4)
        var date1: Date = cal.time;
        cal.set(2022,4,5)
        var date2: Date = cal.time;
        cal.set(2022,3,25)
        var date5: Date = cal.time
        cal.set(2022,3,21)
        var date9: Date=cal.time
        val anketa = Anketa("Anketa 1","Istrazivanje A",date1,date2,date1,5,"Grupa 1a",1f)
        val anketa2 = Anketa("Anketa 6","Istrazivanje C",date9,date5,date9,5,"Grupa 2c",1f)
        AnketaRepository.addMyAnketu(anketa)
        val ankete: List<Anketa> = listOf(anketa2,anketa)
        assertEquals(ankete.get(1),AnketaRepository.getMyAnkete().get(1))
    }
    @Test
    fun getDoneTest(){
        var cal: Calendar = Calendar.getInstance()
        cal.set(2022,4,4)
        var date1: Date = cal.time;
        cal.set(2022,4,5)
        var date2: Date = cal.time;
        cal.set(2022,4,5)
        var date3: Date = cal.time;

        cal.set(2022,3,20)
        var date4: Date = cal.time
        cal.set(2022,3,25)
        var date5: Date = cal.time

        cal.set(2022,5,5)
        var date6: Date = cal.time

        cal.set(2022,5,6)
        var date7: Date=cal.time

        cal.set(2022,3,18)
        var date8: Date=cal.time

        cal.set(2022,3,21)
        var date9: Date=cal.time
        val ankete = listOf(Anketa("Anketa 5","Istrazivanje C",date8,date7,date8,5,"Grupa 1c",1f)
        , Anketa("Anketa 6","Istrazivanje C",date9,date5,date9,5,"Grupa 2c",1f))

        assertEquals(ankete.get(1).naziv,AnketaRepository.getDone().get(1).naziv)
    }
    @Test
    fun getFutureTest(){
        var cal: Calendar = Calendar.getInstance()
        cal.set(2022,4,4)
        var date1: Date = cal.time;
        cal.set(2022,4,5)
        var date2: Date = cal.time;
        cal.set(2022,4,5)
        var date3: Date = cal.time;

        cal.set(2022,3,20)
        var date4: Date = cal.time
        cal.set(2022,3,25)
        var date5: Date = cal.time

        cal.set(2022,5,5)
        var date6: Date = cal.time

        cal.set(2022,5,6)
        var date7: Date=cal.time

        cal.set(2022,3,18)
        var date8: Date=cal.time

        cal.set(2022,3,21)
        var date9: Date=cal.time
        val anketa = Anketa("Anketa 1","Istrazivanje A",
            date1,date2,null,5,"Grupa 1a",0f)
        assertEquals(anketa,AnketaRepository.getFuture().get(0))
    }
    @Test
    fun getNotTakenTest(){
        var cal: Calendar = Calendar.getInstance()
        cal.set(2022,4,4)
        var date1: Date = cal.time;
        cal.set(2022,4,5)
        var date2: Date = cal.time;
        cal.set(2022,4,5)
        var date3: Date = cal.time;

        cal.set(2022,3,20)
        var date4: Date = cal.time
        cal.set(2022,3,25)
        var date5: Date = cal.time

        cal.set(2022,5,5)
        var date6: Date = cal.time

        cal.set(2022,5,6)
        var date7: Date=cal.time

        cal.set(2022,3,18)
        var date8: Date=cal.time

        cal.set(2022,3,21)
        var date9: Date=cal.time
        val ankete = listOf(
            Anketa("Anketa 2","Istrazivanje A",date4, date5, null,5,"Grupa 2a",0f)
            , Anketa("Anketa 4","Istrazivanje B",date4,date5,date4,5,"Grupa 2b",0.7f)
        )
        assertEquals(ankete,AnketaRepository.getNotTaken())
    }
}