package ba.etf.rma22.projekat.data.repositories


import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.mojeAnkete
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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

    /*fun getDone() : List<Anketa> {
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
    }*/

    suspend fun getAll(offset:Int): List<Anketa>? {

            return withContext(Dispatchers.IO) {
                var response = ApiConfig.retrofit.getAnkete(offset)
                val responseBody = response.body()
                return@withContext responseBody
            }

    }
    suspend fun getAll() : List<Anketa>? {
        return withContext(Dispatchers.IO) {
            var offset = 1
            var listaAnketa = listOf<Anketa>()
            while(true){
                var lista = getAll(offset)
                for(i in lista!!)
                listaAnketa = listaAnketa + i

                if(lista.size < 5) break
                offset++
            }

            return@withContext listaAnketa
        }
    }
    //vraća listu svih anketa ili ako je zadan offset odgovarajući page u rezultatima (npr ako je pozvana metoda bez
// parametra vraćaju se sve ankete, a ako je offset 1 vraća se samo prvih 5)

    suspend fun getById(id:Int):Anketa? {
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getAnketaById(id)
            val responseBody = response.body()
            if(responseBody == null) return@withContext null
            return@withContext responseBody
        }
    }
    //vraća jednu anketu koja ima zadani id ili null ako anketa ne postoji

    suspend fun getUpisane():List<Anketa> {
        return withContext(Dispatchers.IO){
            var response = ApiConfig.retrofit.getUpisaneGrupeById(AccountRepository.getHash())
            val responseBody = response.body()
            val lista = mutableListOf<Anketa>()
            for(g in responseBody!!){
                var response2 = ApiConfig.retrofit.getAnketeByGrupaId(g.id)
                val responseBody2 = response2.body()!!
                lista.addAll(responseBody2)
            }
            return@withContext lista
        }
    }
    //vraća listu svih anketa za grupe na kojima je student upisan
}