package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.Grupa
import ba.etf.rma22.projekat.Istrazivanje
import ba.etf.rma22.projekat.grupe

object GrupaRepository {
    fun getGroupsByIstrazivanje(nazivIstrazivanja:String) : List<Grupa> {
        var lista = grupe()
        var novaLista: List<Grupa> = emptyList()
        for(i in lista){
            if(i.nazivIstrazivanja == nazivIstrazivanja)
                novaLista = novaLista + i
        }
        return novaLista
    }

}