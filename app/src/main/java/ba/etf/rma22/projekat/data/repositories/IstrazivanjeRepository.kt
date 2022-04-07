package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.Istrazivanje
import ba.etf.rma22.projekat.istrazivanja
import ba.etf.rma22.projekat.mojeIstrazivanje

object IstrazivanjeRepository {
    fun getIstrazivanjeByGodina(godina:Int) : List<Istrazivanje>{
        var lista = getAll()
        var novaLista: List<Istrazivanje> = emptyList()
        for(i in lista){
         if(i.godina == godina)
             novaLista = (novaLista + i)
        }
        return novaLista
    }
    fun getAll() : List<Istrazivanje> {
        return istrazivanja()
    }
    var listaUpisanih = mojeIstrazivanje()
    fun dodajUpisani(istrazivanje: Istrazivanje): Unit {
        var sadrzi: Boolean = false
        for(i in IstrazivanjeRepository.listaUpisanih) {
            if (istrazivanje.naziv == i.naziv) {
                sadrzi = true
            }
        }
        if(!sadrzi)
            IstrazivanjeRepository.listaUpisanih = IstrazivanjeRepository.listaUpisanih + istrazivanje
    }
    fun getUpisani() : List<Istrazivanje> {
        return listaUpisanih
    }
}