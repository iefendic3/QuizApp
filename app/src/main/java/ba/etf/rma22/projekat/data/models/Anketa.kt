package ba.etf.rma22.projekat.data.models

import java.util.*

data class Anketa(
    val naziv: String,
    val nazivIstrazivanja: String,
    val datumPocetak: Date,
    val datumKraj: Date,
    val datumRada: Date?,
    val trajanje: Int,
    val nazivGrupe: String,
    val progres: Float
) : Comparable<Anketa> {
    override fun compareTo(other: Anketa): Int {
        if(other.datumPocetak>this.datumPocetak) return -1
        else if(other.datumPocetak==this.datumPocetak) return 0
        return 1
    }
}
