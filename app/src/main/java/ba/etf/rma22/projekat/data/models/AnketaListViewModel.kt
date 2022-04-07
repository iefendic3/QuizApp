package ba.etf.rma22.projekat.data.models


import ba.etf.rma22.projekat.data.repositories.*

class AnketaListViewModel {
    fun getAnkete():List<Anketa> {
        return DummyRepository.getAnkete()
    }

}