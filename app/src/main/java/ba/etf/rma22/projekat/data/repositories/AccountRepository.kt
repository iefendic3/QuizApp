package ba.etf.rma22.projekat.data.repositories

object AccountRepository {
    var acHash: String = "846c0a86-7dd2-4449-833c-24f412d48f55"
    fun postaviHash(acHash1:String):Boolean{
        acHash = acHash1
        if(acHash != null)
        return true

        return false
    }
    //postavlja (lokalno, ne putem web servisa) hash studenta koji će biti korišten u drugim repozitorijima,
// vraća true ukoliko je hash postavljen, false inače
    fun getHash():String{
        return acHash
    }
    //vraća hash studenta koji je postavljen
}