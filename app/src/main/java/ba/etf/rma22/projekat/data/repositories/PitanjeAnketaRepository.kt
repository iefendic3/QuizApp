package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.Pitanje
import ba.etf.rma22.projekat.pitanja
import ba.etf.rma22.projekat.pitanjaAnkete
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object PitanjeAnketaRepository {
    /*fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String): MutableList<Pitanje>{
        var lista = mutableListOf<Pitanje>()
        var lista2 = pitanja()
        for(i in pitanjaAnkete()){
            for(j in lista2)
            if(j.naziv == i.naziv && i.anketa == nazivAnkete){
                lista.add(j)
            }
        }
        return lista
    }*/

    suspend fun getPitanja(idAnkete:Int):List<Pitanje>? {
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getPitanjaByAnketaId(idAnkete)
            val responseBody = response.body()
            return@withContext responseBody
        }
    }
    //vraća sva pitanja na anketi sa zadanim id-em
}