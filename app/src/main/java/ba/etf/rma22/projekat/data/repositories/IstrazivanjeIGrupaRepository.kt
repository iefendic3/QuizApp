package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.models.RMA22DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")

object IstrazivanjeIGrupaRepository {

    private lateinit var context: Context
    fun setContext(cont: Context) {
        context = cont
    }

    suspend fun getIstrazivanja(offset:Int):List<Istrazivanje>{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getIstrazivanja(offset)
            val responseBody = response.body()
            val db = RMA22DB.getInstance(context)
            db.istrazivanjeDao().izbrisiSve()
            db.istrazivanjeDao().insertAll(responseBody!!)
            return@withContext responseBody
        }
    }
    suspend fun getIstrazivanja():List<Istrazivanje>{
        return withContext(Dispatchers.IO) {
            var i = 1
            var lista = mutableListOf<Istrazivanje>()
            while (true){
                var response = ApiConfig.retrofit.getIstrazivanja(i)
                lista.addAll(response.body()!!)
                if(response.body()!!.size<5){
                    break
                }
                i++
            }
            val db = RMA22DB.getInstance(context)
            db.istrazivanjeDao().izbrisiSve()
            db.istrazivanjeDao().insertAll(lista)
            return@withContext lista
        }
    }
    suspend fun getGrupe():List<Grupa>{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getGrupe()
            val responseBody = response.body()
            val db = RMA22DB.getInstance(context)
            db.grupaDao().izbrisiSve()
            db.grupaDao().insertAll(responseBody!!)
            return@withContext responseBody!!
        }
    }
    suspend fun getGrupeZaIstrazivanje(idIstrazivanja:Int) : List<Grupa>{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getGrupe()
            val responseBody = response.body()
            return@withContext responseBody!!.filter { grupa -> grupa.istrazivanjeId==idIstrazivanja }
        }
    }
    suspend fun upisiUGrupu(idGrupa:Int):Boolean{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.dodajStudentaUGrupuById(idGrupa, AccountRepository.getHash())
            val responseBody = response.body()
            if(responseBody!!.message.contains("not found", ignoreCase = true)){
                return@withContext false
            }
            return@withContext true
        }
    }
    suspend fun getUpisaneGrupe():List<Grupa>{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getUpisaneGrupeById(AccountRepository.getHash())
            val responseBody = response.body()
            if(responseBody==null){
                return@withContext emptyList()
            }
            return@withContext responseBody
        }
    }
    suspend fun getIstrazivanjaZaGrupu(idGrupa:Int) : List<Istrazivanje>{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getIstrazivanjaByGrupaId(idGrupa)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }
    suspend fun getGrupeZaAnketu(idAnketa : Int) : List<Grupa>{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getGrupeByAnketaId(idAnketa)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }
    suspend fun getIstrazivanjeZaId(idIstrazivanja: Int) : Istrazivanje{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getIstrazivanjeById(idIstrazivanja)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }
}