package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.GetOdgovorBodoviResponse
import ba.etf.rma22.projekat.Odgovor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

object OdgovorRepository {

    suspend fun getOdgovoriAnketa(idAnkete:Int):List<Odgovor> {

        return withContext(Dispatchers.IO) {
            var listaPokusaja = ApiConfig.retrofit.getPoceteAnkete(AccountRepository.getHash())
            var lista = listaPokusaja?.body()
            var idPokusaja: Int = -1
            for(i in lista!!)
                if(i.AnketumId==idAnkete){
                    idPokusaja = i.id
                }

            var response = ApiConfig.retrofit.getOdgovoriById(AccountRepository.getHash(),idPokusaja)
            val responseBody = response.body()
            return@withContext responseBody!!
        }

    }
    //vraća listu odgovora za anketu, praznu listu ako student nije odgovarao ili nije upisan na zadanu anketu

    suspend fun postaviOdgovorAnketa(idAnketaTaken:Int,idPitanje:Int,odgovor:Int):Int
    {
        try {
            return withContext(Dispatchers.IO) {

                var pokusaji = ApiConfig.retrofit.getPoceteAnkete(AccountRepository.getHash())
                var id = 0
                for(i in pokusaji?.body()!!){
                    if(i.id == idAnketaTaken) {
                        id = i.AnketumId
                        break
                    }
                }
                var pitanja = ApiConfig.retrofit.getPitanjaByAnketaId(id)
                var pitanjaBody = pitanja.body()

                var odgovori = ApiConfig.retrofit.getOdgovoriById(AccountRepository.getHash(),idAnketaTaken)
                var odgovoriBody = odgovori.body()

                val odgovorPitanje = (odgovoriBody!!.size+1).toFloat()/pitanjaBody!!.size.toFloat()
                val progres = izracunajProgres((odgovorPitanje*100).roundToInt())
                val bodovi = GetOdgovorBodoviResponse(odgovor,idPitanje,progres)

                var response = ApiConfig.retrofit.dodajOdgovorById(AccountRepository.getHash(),idAnketaTaken,bodovi)
                val responseBody = response.body()

                return@withContext bodovi.progres
            }
        } catch(e: Exception){
            return -1
        }
    }
    //funkcija postavlja odgovor sa indeksom odgovor na pitanje sa id-em idPitanje u okviru pokušaja sa id-em idAnketaTaken.
// Funkcija vraća progres (0-100) na anketi nakon odgovora ili -1 ukoliko ima neka greška u zahtjevu
    private fun izracunajProgres(odgovorPitanje: Int): Int{
        var x = odgovorPitanje
        if(x >= 90 && x <= 100){
            x = 100
        }
        else if(x>=70 && x<=89){
            x=80
        }
        else if(x>=50 && x<=69){
            x=60
        }
        else if(x>=30 && x<=59){
            x=40
        }
        else if(x>=10 && x<=29){
            x=20
        }
        else if(x>=0 && x<=9){
            x=0
        }
        return x
    }
}