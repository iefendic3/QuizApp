package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.AnketaTaken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TakeAnketaRepository {

    suspend fun zapocniAnketu(idAnkete:Int): AnketaTaken? {
//        return withContext(Dispatchers.IO){
//            try{
//                val pocete = getPoceteAnkete()
//                if(pocete!=null){
//                    val filtrirano =
//                        pocete.filter {anketaTaken ->
//                            anketaTaken.AnketumId == idAnkete &&
//                                    anketaTaken.student == AccountRepository.getHash()
//                        }
//                    if(filtrirano?.count()!=0)
//                        return@withContext filtrirano!!.last()
//                }
//                var response = ApiConfig.retrofit.zapocniOdgovaranje(AccountRepository.getHash(),idAnkete)
//                val responseBody = response.body()
//                return@withContext responseBody
//
//            } catch (e: Exception){
//                return@withContext null
//            }
//        }
        return withContext(Dispatchers.IO) {
            val ankete = ApiConfig.retrofit.getPoceteAnkete(AccountRepository.getHash())
            val body = ankete?.body()
            for(i in body!!){
                if(idAnkete==i.AnketumId){
                    return@withContext null
                }
            }
            var response = ApiConfig.retrofit.zapocniAnketu(AccountRepository.getHash(),idAnkete)
            val responseBody = response.body()
            return@withContext responseBody!!
        }
    }

    //kreira pokušaj za anketu, vraća kreirani pokušaj ili null u slučaju greške

    suspend fun getPoceteAnkete(): List<AnketaTaken>? {

        return withContext(Dispatchers.IO) {
            var response = ApiConfig.retrofit.getPoceteAnkete(AccountRepository.getHash())
            val responseBody = response?.body()
            if(responseBody!!.isEmpty()){
                return@withContext null
            }
            return@withContext responseBody
        }
    }
    //vraća listu pokušaja ili null ukoliko student nema niti jednu započetu anketu
}