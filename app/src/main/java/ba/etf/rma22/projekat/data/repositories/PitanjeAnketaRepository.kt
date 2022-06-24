package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.models.RMA22DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")

object PitanjeAnketaRepository {

    private lateinit var context: Context
    fun setContext(cont: Context) {
        context = cont
    }

    suspend fun getPitanja(idAnkete:Int): List<Pitanje>{
        return withContext(Dispatchers.IO) {
            val response = ApiConfig.retrofit.getPitanjaByAnketaId(idAnkete)
            val responseBody = response.body()
            val db = RMA22DB.getInstance(context)
            db.pitanjeDao().izbrisiSve()
            db.pitanjeDao().insertAll(responseBody!!)
            return@withContext responseBody
        }
    }
}