package ba.etf.rma22.projekat.data.models


import ba.etf.rma22.projekat.data.repositories.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class AnketaListViewModel {
    private val scope = MainScope()
    fun getAnkete():List<Anketa> {
        return DummyRepository.getAnkete()
    }

    fun getAll(onSuccess: (ankete: List<Anketa>) -> Unit,
                     onError: () -> Unit){
        // Create a new coroutine on the UI thread
        scope.launch{
            // Make the network call and suspend execution until it finishes
            val result = AnketaRepository.getAll()

            // Display result of the network request to the user
            when (result) {
                is List<Anketa> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }

    fun getUpisane(onSuccess: (ankete: List<Anketa>) -> Unit,
               onError: () -> Unit){
        // Create a new coroutine on the UI thread
        scope.launch{
            // Make the network call and suspend execution until it finishes
            val result = AnketaRepository.getUpisane()

            // Display result of the network request to the user
            when (result) {
                is List<Anketa> -> onSuccess?.invoke(result)
                else-> onError?.invoke()
            }
        }
    }



}