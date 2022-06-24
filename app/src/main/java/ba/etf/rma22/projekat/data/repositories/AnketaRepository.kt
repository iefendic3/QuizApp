package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")

object AnketaRepository {

    private lateinit var context: Context
    fun setContext(cont: Context) {
        context = cont
    }

    suspend fun getAll(offset : Int) : List<Anketa>{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getAnkete(offset)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getAll() : List<Anketa>{
        return withContext(Dispatchers.IO) {
            var lista = mutableListOf<Anketa>()
            var i = 1
            while(true){
                var response = ApiConfig.retrofit.getAnkete(i)
                lista.addAll(response.body()!!)
                if(response.body()!!.size < 5){
                    break
                }
                i++
            }
            val db = RMA22DB.getInstance(context)
            db.anketaDao().izbrisiSve()

            db.anketaDao().insertAll(lista)
            return@withContext lista
        }
    }

    suspend fun getById(id : Int) : Anketa {
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getAnketaById(id)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    suspend fun getUpisane() : List<Anketa> {
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getGrupeStudenta(AccountRepository.getHash())
            val responseBody = response.body()
            val lista = mutableListOf<Anketa>()
            for(grupa in responseBody!!){
                var response2 = ApiConfig.retrofit.getAnketeByGrupaId(grupa.id)
                val responsebody2  = response2.body()!!
                lista.addAll(responsebody2)
            }
            return@withContext lista
        }
    }

    suspend fun getUpisaneDB() : List<Anketa> {
        return withContext(Dispatchers.IO){
            val db = RMA22DB.getInstance(context)
            val ankete = db.anketaDao().getAll()
            return@withContext ankete
        }
    }

    suspend fun getSveAnkete() : List<MojaAnketa>{
        return withContext(Dispatchers.IO){
            var lista = mutableListOf<Anketa>()
            var i = 1
            while(true){
                var response = ApiConfig.retrofit.getAnkete(i)
                lista.addAll(response.body()!!)
                if(response.body()!!.size < 5){
                    break
                }
                i++
            }
            var lista2 = mutableListOf<MojaAnketa>()
            for(anketa in lista){
                var response = ApiConfig.retrofit.getGrupeByAnketaId(anketa.id)
                for(grupa in response.body()!!){
                    lista2.add(MojaAnketa(anketa.id,grupa.id, grupa.istrazivanjeId, false))
                }
            }
            return@withContext lista2
        }
    }
}