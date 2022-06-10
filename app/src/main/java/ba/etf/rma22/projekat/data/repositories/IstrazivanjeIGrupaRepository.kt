package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object IstrazivanjeIGrupaRepository {
    suspend fun getIstrazivanja(offset:Int):List<Istrazivanje>? {
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getIstrazivanja(offset)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
    suspend fun getIstrazivanja() : List<Istrazivanje>? {
        return withContext(Dispatchers.IO) {
            var offset = 1
            var listaIstrazivanja = listOf<Istrazivanje>()
            while(true){
                var lista = IstrazivanjeIGrupaRepository.getIstrazivanja(offset)
                for(i in lista!!)
                    listaIstrazivanja = listaIstrazivanja + i

                if(lista.size < 5) break
                offset++
            }

            return@withContext listaIstrazivanja
        }
    }
    //vraća sva istraživanja ili ako je zadan offset odgovarajući page u rezultatima

    suspend fun getGrupe(): List<Grupa>? {
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getGrupe()
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
    //vraća sve grupe

    fun getGrupeZaIstrazivanje(idIstrazivanja:Int):List<Grupa> {
        return listOf()
    }
    //vraća grupe na istraživanju sa id-em idIstrazivanja

    suspend fun upisiUGrupu(idGrupa:Int):Boolean {
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.dodajStudentaUGrupuById(idGrupa, AccountRepository.acHash)
            val responseBody = response.body()
            return@withContext true
        }

    }
    //upisuje studenta u grupu sa id-em idGrupa i vraća true ili vraća false ako nije moguće upisati studenta

    suspend fun getUpisaneGrupe():List<Grupa>? {
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getUpisaneGrupeById(AccountRepository.acHash)
            val responseBody = response.body()
            if(responseBody == null) return@withContext emptyList()
            return@withContext responseBody
        }
    }
    //vraća grupe u kojima je student upisan

}