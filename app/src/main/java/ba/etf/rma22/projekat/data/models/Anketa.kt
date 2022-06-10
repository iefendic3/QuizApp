package ba.etf.rma22.projekat.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Anketa(
    /*val naziv: String,
    val nazivIstrazivanja: String,
    val datumPocetak: Date,
    val datumKraj: Date,
    val datumRada: Date?,
    val trajanje: Int,
    val nazivGrupe: String,
    val progres: Float*/
    @SerializedName("id") val id: Int,
    @SerializedName("naziv") val naziv: String,
    @SerializedName("datumPocetak") val datumPocetak: Date,
    @SerializedName("datumKraj") val datumKraj: Date,
    @SerializedName("trajanje") val trajanje: Int
) : Comparable<Anketa> {
    override fun compareTo(other: Anketa): Int {
        if(other.datumPocetak>this.datumPocetak) return -1
        else if(other.datumPocetak==this.datumPocetak) return 0
        return 1
    }
}
