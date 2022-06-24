package ba.etf.rma22.projekat.data.repositories

import android.annotation.SuppressLint
import android.content.Context
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.RMA22DB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@SuppressLint("StaticFieldLeak")

object TakeAnketaRepository {

    private lateinit var context: Context
    fun setContext(cont: Context) {
        context = cont
    }

    suspend fun zapocniAnketu(anketaId : Int) : AnketaTaken?{
        return withContext(Dispatchers.IO) {
            val resp = ApiConfig.retrofit.getPoceteAnkete(AccountRepository.getHash())
            val respBody = resp.body()
            for(anketaTaken in respBody!!){
                if(anketaTaken.AnketumId==anketaId){
                    return@withContext null
                }
            }
            var response = ApiConfig.retrofit.zapocniAnketu(AccountRepository.getHash(),anketaId)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }
    suspend fun getPoceteAnkete() : List<AnketaTaken>?{
        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getPoceteAnkete(AccountRepository.getHash())
            val responseBody = response.body()
            if(responseBody!!.isEmpty()){
                return@withContext null
            }
            return@withContext responseBody
        }
    }
}