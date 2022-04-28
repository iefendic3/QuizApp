package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.Pitanje
import ba.etf.rma22.projekat.pitanja
import ba.etf.rma22.projekat.pitanjaAnkete

object PitanjeAnketaRepository {
    fun getPitanja(nazivAnkete: String, nazivIstrazivanja: String): MutableList<Pitanje>{
        var lista = mutableListOf<Pitanje>()
        var lista2 = pitanja()
        for(i in pitanjaAnkete()){
            for(j in lista2)
            if(j.naziv == i.naziv && i.anketa == nazivAnkete){
                lista.add(j)
            }
        }
        return lista
    }
}