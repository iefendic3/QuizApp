package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.ankete
import ba.etf.rma22.projekat.data.models.Anketa


object DummyRepository {
    fun getAnkete(): List<Anketa>{
        return ankete()
    }

}