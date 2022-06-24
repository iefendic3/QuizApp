package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.models.RMA22DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")

object AccountRepository {

    private lateinit var context: Context
    fun setContext(cont: Context) {
        context = cont
    }

    var acHash : String = "846c0a86-7dd2-4449-833c-24f412d48f55"

    suspend fun postaviHash(accHash: String): Boolean {
        return withContext(Dispatchers.IO) {
            var db = RMA22DB.getInstance(context)
            var hashDB = db.accountDao().getHash()
            if(accHash != hashDB) {
                if(hashDB!=null) {
                    ApiConfig.retrofit.obrisiKorisnikaById(hashDB)
                }
                db.accountDao().izbrisiSve()
                db.grupaDao().izbrisiSve()
                db.anketaDao().izbrisiSve()
                db.anketaTakenDao().izbrisiSve()
                db.pitanjeDao().izbrisiSve()
                db.odgovorDao().izbrisiSve()
                db.istrazivanjeDao().izbrisiSve()
                db.accountDao().upisi(accHash)
                acHash = accHash
            }
            return@withContext true
        }
    }

    fun getHash():String {
        return acHash
    }
}