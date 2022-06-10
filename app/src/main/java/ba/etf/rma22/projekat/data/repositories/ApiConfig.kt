package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.Api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    var baseURL = "https://rma22ws.herokuapp.com"

    val retrofit = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build().create(
        Api::class.java)

    fun postaviBaseURL(baseUrl: String): Unit {
        baseURL = baseUrl
    }
//postavlja base url za web servise, ukoliko se ova metoda ne pozove koristi se defaultni url
}