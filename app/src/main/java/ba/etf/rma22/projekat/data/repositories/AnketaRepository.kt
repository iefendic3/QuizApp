package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.mojeAnkete
import java.util.*

object AnketaRepository {
    private var mojeAnkete: List<Anketa> = mojeAnkete()
    fun addMyAnketu(anketa: Anketa): Unit {
        var sadrzi: Boolean = false
        for(i in mojeAnkete) {
            if (anketa.naziv == i.naziv) {
                sadrzi = true
            }
        }
        if(!sadrzi)
        mojeAnkete = mojeAnkete + anketa
    }
   fun getMyAnkete() : List<Anketa> {
       return mojeAnkete
   }
    fun getAll() : List<Anketa> {
        return ankete()
    }
    fun getDone() : List<Anketa> {
        var lista = getMyAnkete()
        var novaLista: List<Anketa> = emptyList()
        for(i in lista ){
            if(i.datumRada != null && i.progres==1f) {
                novaLista = novaLista + i
            }
        }
        return novaLista
    }
    fun getFuture() : List<Anketa> {
        var calendar: Calendar = Calendar.getInstance()
        calendar.set(2022,4,3)
        var refDate = calendar.time
        var lista = getMyAnkete()
        var novaLista: List<Anketa> = emptyList()
        for(i in lista ){
            if(refDate.compareTo(i.datumPocetak) < 0) {
                novaLista = novaLista + i
            }
        }
        return novaLista
    }
    fun getNotTaken() : List<Anketa> {
        var calendar: Calendar = Calendar.getInstance()
        calendar.set(2022,4,3)
        var refDate = calendar.time
        var lista = getMyAnkete()
        var novaLista: List<Anketa> = emptyList()
        for(i in lista ){
            if((refDate.compareTo(i.datumKraj) >0
                        && i.datumRada == null) || (refDate.compareTo(i.datumKraj) >0
                        && i.datumRada != null && i.progres<1f)) {
                novaLista = novaLista + i
            }
        }
        return novaLista
    }

}