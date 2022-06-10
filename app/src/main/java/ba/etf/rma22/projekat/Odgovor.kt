package ba.etf.rma22.projekat

import com.google.gson.annotations.SerializedName

data class Odgovor (
    @SerializedName("id") val id: Int,
    @SerializedName("odgovoreno") val odgovoreno: Int,
    @SerializedName("AnketaTakenId") val anketaTakenId: Int,
    @SerializedName("PitanjeId") val pitanjeId: Int,

    )