package ba.etf.rma22.projekat

data class Pitanje (
    val naziv: String, //jedinstveni naziv pitanja u okviru ankete u kojoj se nalazi
    val tekst: String, //tekst pitanja
    val opcije: List<String> //lista ponuđenih odgovora
)