package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.models.Odgovor
import ba.etf.rma22.projekat.data.models.OdgovorPitanjeBodovi
import ba.etf.rma22.projekat.data.models.RMA22DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

@SuppressLint("StaticFieldLeak")

object OdgovorRepository {

    private lateinit var context: Context
    fun setContext(cont: Context) {
        context = cont
    }

    suspend fun getOdgovoriAnketa(anketaId : Int) : List<Odgovor>{
        return withContext(Dispatchers.IO) {
            var resp = ApiConfig.retrofit.getPoceteAnkete(AccountRepository.getHash())
            val respBody = resp.body()
            var id = -1
            for(anketaTaken in respBody!!){
                if(anketaTaken.AnketumId==anketaId){
                    id = anketaTaken.id
                }
            }
            var response = ApiConfig.retrofit.getOdgovoriById(AccountRepository.getHash(),id)
            val responseBody = response.body()

            return@withContext responseBody!!
        }
    }

    suspend fun postaviOdgovorAnketa(idAnketaTaken:Int,idPitanje:Int,odgovor:Int) : Int{
        return withContext(Dispatchers.IO) {
            val resp = ApiConfig.retrofit.getPoceteAnkete(AccountRepository.getHash())
            val respBody = resp.body()
            var idAnkete : Int = -1
            for(anketaTaken in respBody!!){
                if(anketaTaken.id==idAnketaTaken){
                    idAnkete=anketaTaken.AnketumId
                    break
                }
            }

            val resp2 = ApiConfig.retrofit.getPitanjaByAnketaId(idAnkete)
            val respBody2 = resp2.body()
            val brojPitanja = respBody2!!.size

            val resp3 = ApiConfig.retrofit.getOdgovoriById(AccountRepository.getHash(),idAnketaTaken)
            val respBody3 = resp3.body()
            val brojOdgovora = respBody3!!.size + 1

            val temp: Float = brojOdgovora.toFloat() / brojPitanja.toFloat()
            val progress = odrediPostotak((temp * 100).roundToInt())
            val odgovorPitanjeBodovi = OdgovorPitanjeBodovi(odgovor, idPitanje, progress)

            val response = ApiConfig.retrofit.dodajOdgovorById(AccountRepository.getHash(),idAnketaTaken,odgovorPitanjeBodovi)
            val responseBody = response.body()
            if(responseBody==null){
                return@withContext -1
            }
            val db = RMA22DB.getInstance(context)
            var temp2 = true
            for(odg in db.odgovorDao().getAll()){
                if(odg.pitanjeId==idPitanje){
                    temp2 = false
                }
            }
            if(temp2) {

            }

            return@withContext odgovorPitanjeBodovi.progres
        }
    }

    private fun odrediPostotak(x : Int) : Int {
        var temp = x
        if(temp >= 0 && temp<=9){
            temp = 0
        }
        else if(temp>=10 && temp<=29){
            temp=20
        }
        else if(temp>=30 && temp<=49){
            temp=40
        }
        else if(temp>=50 && temp<=69){
            temp=60
        }
        else if(temp>=70 && temp<=89){
            temp = 80
        }
        else if(temp>=90 && temp<=100){
            temp = 100
        }
        return temp
    }
}