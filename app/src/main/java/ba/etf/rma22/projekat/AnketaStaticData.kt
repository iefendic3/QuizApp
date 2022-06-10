package ba.etf.rma22.projekat
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import java.util.*

fun ankete(): List<Anketa>{
    var cal: Calendar = Calendar.getInstance()
    cal.set(2022,3,4)
    var date1: Date = cal.time;
    cal.set(2022,3,5)
    var date2: Date = cal.time;
    cal.set(2022,4,5)
    var date3: Date = cal.time;

    cal.set(2022,2,20)
    var date4: Date = cal.time
    cal.set(2022,2,25)
    var date5: Date = cal.time

    cal.set(2022,5,5)
    var date6: Date = cal.time

    cal.set(2022,5,6)
    var date7: Date=cal.time

    cal.set(2022,2,18)
    var date8: Date=cal.time

    cal.set(2022,2,21)
    var date9: Date=cal.time

    cal.set(2022, 6,21)
    var date10: Date = cal.time

    cal.set(2022, 7, 22)
    var date11: Date = cal.time

    return listOf(
        /*Anketa("Anketa 1","Istrazivanje A",
            date1,date2,null,5,"Grupa 1a",0f),
        Anketa("Anketa 2","Istrazivanje A",date4, date5, null,5,"Grupa 2a",0f)
        , Anketa("Anketa 3","Istrazivanje B",date4,date6,null,5,"Grupa 1b",0f)
        , Anketa("Anketa 4","Istrazivanje B",date4,date5,date4,5,"Grupa 2b",0.7f)
        , Anketa("Anketa 5","Istrazivanje C",date8,date7,date8,5,"Grupa 1c",1f)
        , Anketa("Anketa 6","Istrazivanje C",date9,date5,date9,5,"Grupa 2c",1f)

        , Anketa("Anketa 7","Istrazivanje D",date9,date5,date9,5,"Grupa 1d",0.5f)
        , Anketa("Anketa 8","Istrazivanje D",date10,date11,null,5,"Grupa 2d",0f)

        , Anketa("Anketa 9","Istrazivanje E",date9,date5,date9,5,"Grupa 1e",1f)
        , Anketa("Anketa 10","Istrazivanje E",date10,date11,null,5,"Grupa 2e",0f)
*/
    )
}
fun mojeAnkete(): List<Anketa>{
    var cal: Calendar = Calendar.getInstance()
    cal.set(2022,2,25)
    var date5: Date = cal.time
    cal.set(2022,2,21)
    var date9: Date=cal.time
    return listOf(
       // Anketa("Anketa 6","Istrazivanje C",date9,date5,date9,5,"Grupa 2c",1f)
    )
}

fun mojeIstrazivanje(): List<Istrazivanje>{
    return listOf(
       // Istrazivanje("Istrazivanje C",3)
    )
}

fun istrazivanja(): List<Istrazivanje>{
    return listOf(
//        Istrazivanje("Istrazivanje A",1),
//        Istrazivanje("Istrazivanje B",2),
//        Istrazivanje("Istrazivanje C",3),
//        Istrazivanje("Istrazivanje D",4),
//        Istrazivanje("Istrazivanje E",5)
    )
}
fun grupe(): List<Grupa>{
    return listOf(
//        Grupa("Grupa 1a","Istrazivanje A"),
//        Grupa("Grupa 2a","Istrazivanje A"),
//
//        Grupa("Grupa 1b","Istrazivanje B"),
//        Grupa("Grupa 2b","Istrazivanje B"),
//
//        Grupa("Grupa 1c","Istrazivanje C"),
//        Grupa("Grupa 2c","Istrazivanje C"),
//
//        Grupa("Grupa 1d","Istrazivanje D"),
//        Grupa("Grupa 2d","Istrazivanje D"),
//
//        Grupa("Grupa 1e","Istrazivanje E"),
//        Grupa("Grupa 2e","Istrazivanje E")
    )
}

fun odgovori(): List<String>{
    return listOf("a","b","c")
}

fun pitanja(): List<Pitanje>{
    var odgovori1 = listOf<String>("a1","b1","c1")
    var odgovori2 = listOf<String>("a2","b2","c2")
    var odgovori3 = listOf<String>("a3","b3","c3")
    var odgovori4 = listOf<String>("a4","b4","c4")
    var odgovori5 = listOf<String>("a5","b5","c5")
    return listOf(
//        Pitanje("Pitanje 1a", "Ovo je tekst Pitanja 1a",odgovori1),
//        Pitanje("Pitanje 2a", "Ovo je tekst Pitanja 2a",odgovori2),
//        Pitanje("Pitanje 3a", "Ovo je tekst Pitanja 3a",odgovori3),
//        Pitanje("Pitanje 4a", "Ovo je tekst Pitanja 4a",odgovori4),
//        Pitanje("Pitanje 5a", "Ovo je tekst Pitanja 5a",odgovori5),
//
//
//        Pitanje("Pitanje 1b", "Ovo je tekst Pitanja 1b",odgovori1),
//        Pitanje("Pitanje 2b", "Ovo je tekst Pitanja 2b",odgovori2),
//        Pitanje("Pitanje 3b", "Ovo je tekst Pitanja 3b",odgovori3),
//        Pitanje("Pitanje 4b", "Ovo je tekst Pitanja 4b",odgovori4),
//        Pitanje("Pitanje 5b", "Ovo je tekst Pitanja 5b",odgovori5),
//
//        Pitanje("Pitanje 1c", "Ovo je tekst Pitanja 1c",odgovori1),
//        Pitanje("Pitanje 2c", "Ovo je tekst Pitanja 2c",odgovori2),
//        Pitanje("Pitanje 3c", "Ovo je tekst Pitanja 3c",odgovori3),
//        Pitanje("Pitanje 4c", "Ovo je tekst Pitanja 4c",odgovori4),
//        Pitanje("Pitanje 5c", "Ovo je tekst Pitanja 5c",odgovori5),
//
//        Pitanje("Pitanje 1d", "Ovo je tekst Pitanja 1d",odgovori1),
//        Pitanje("Pitanje 2d", "Ovo je tekst Pitanja 2d",odgovori2),
//        Pitanje("Pitanje 3d", "Ovo je tekst Pitanja 3d",odgovori3),
//        Pitanje("Pitanje 4d", "Ovo je tekst Pitanja 4d",odgovori4),
//        Pitanje("Pitanje 5d", "Ovo je tekst Pitanja 5d",odgovori5),
//
//        Pitanje("Pitanje 1e", "Ovo je tekst Pitanja 1e",odgovori1),
//        Pitanje("Pitanje 2e", "Ovo je tekst Pitanja 2e",odgovori2),
//        Pitanje("Pitanje 3e", "Ovo je tekst Pitanja 3e",odgovori3),
//        Pitanje("Pitanje 4e", "Ovo je tekst Pitanja 4e",odgovori4),
//        Pitanje("Pitanje 5e", "Ovo je tekst Pitanja 5e",odgovori5),
//
//        Pitanje("Pitanje 1f", "Ovo je tekst Pitanja 1f",odgovori1),
//        Pitanje("Pitanje 2f", "Ovo je tekst Pitanja 2f",odgovori2),
//        Pitanje("Pitanje 3f", "Ovo je tekst Pitanja 3f",odgovori3),
//        Pitanje("Pitanje 4f", "Ovo je tekst Pitanja 4f",odgovori4),
//        Pitanje("Pitanje 5f", "Ovo je tekst Pitanja 5f",odgovori5),
//
//        Pitanje("Pitanje 1g", "Ovo je tekst Pitanja 1g",odgovori1),
//        Pitanje("Pitanje 2g", "Ovo je tekst Pitanja 2g",odgovori2),
//        Pitanje("Pitanje 3g", "Ovo je tekst Pitanja 3g",odgovori3),
//        Pitanje("Pitanje 4g", "Ovo je tekst Pitanja 4g",odgovori4),
//        Pitanje("Pitanje 5g", "Ovo je tekst Pitanja 5g",odgovori5),
//
//        Pitanje("Pitanje 1h", "Ovo je tekst Pitanja 1h",odgovori1),
//        Pitanje("Pitanje 2h", "Ovo je tekst Pitanja 2h",odgovori2),
//        Pitanje("Pitanje 3h", "Ovo je tekst Pitanja 3h",odgovori3),
//        Pitanje("Pitanje 4h", "Ovo je tekst Pitanja 4h",odgovori4),
//        Pitanje("Pitanje 5h", "Ovo je tekst Pitanja 5h",odgovori5),
//
//        Pitanje("Pitanje 1i", "Ovo je tekst Pitanja 1i",odgovori1),
//        Pitanje("Pitanje 2i", "Ovo je tekst Pitanja 2i",odgovori2),
//        Pitanje("Pitanje 3i", "Ovo je tekst Pitanja 3i",odgovori3),
//        Pitanje("Pitanje 4i", "Ovo je tekst Pitanja 4i",odgovori4),
//        Pitanje("Pitanje 5i", "Ovo je tekst Pitanja 5i",odgovori5),
//
//        Pitanje("Pitanje 1j", "Ovo je tekst Pitanja 1j",odgovori1),
//        Pitanje("Pitanje 2j", "Ovo je tekst Pitanja 2j",odgovori2),
//        Pitanje("Pitanje 3j", "Ovo je tekst Pitanja 3j",odgovori3),
//        Pitanje("Pitanje 4j", "Ovo je tekst Pitanja 4j",odgovori4),
//        Pitanje("Pitanje 5j", "Ovo je tekst Pitanja 5j",odgovori5)
    )
}

fun pitanjaAnkete(): List<PitanjeAnketa>{
    return listOf(
        PitanjeAnketa("Pitanje 1a", "Anketa 1"),
        PitanjeAnketa("Pitanje 2a", "Anketa 1"),
        PitanjeAnketa("Pitanje 3a", "Anketa 1"),
        PitanjeAnketa("Pitanje 4a", "Anketa 1"),
        PitanjeAnketa("Pitanje 5a", "Anketa 1"),

        PitanjeAnketa("Pitanje 1b", "Anketa 2"),
        PitanjeAnketa("Pitanje 2b", "Anketa 2"),
        PitanjeAnketa("Pitanje 3b", "Anketa 2"),
        PitanjeAnketa("Pitanje 4b", "Anketa 2"),
        PitanjeAnketa("Pitanje 5b", "Anketa 2"),

        PitanjeAnketa("Pitanje 1c", "Anketa 3"),
        PitanjeAnketa("Pitanje 2c", "Anketa 3"),
        PitanjeAnketa("Pitanje 3c", "Anketa 3"),
        PitanjeAnketa("Pitanje 4c", "Anketa 3"),
        PitanjeAnketa("Pitanje 5c", "Anketa 3"),

        PitanjeAnketa("Pitanje 1d", "Anketa 4"),
        PitanjeAnketa("Pitanje 2d", "Anketa 4"),
        PitanjeAnketa("Pitanje 3d", "Anketa 4"),
        PitanjeAnketa("Pitanje 4d", "Anketa 4"),
        PitanjeAnketa("Pitanje 5d", "Anketa 4"),

        PitanjeAnketa("Pitanje 1e", "Anketa 5"),
        PitanjeAnketa("Pitanje 2e", "Anketa 5"),
        PitanjeAnketa("Pitanje 3e", "Anketa 5"),
        PitanjeAnketa("Pitanje 4e", "Anketa 5"),
        PitanjeAnketa("Pitanje 5e", "Anketa 5"),

        PitanjeAnketa("Pitanje 1f", "Anketa 6"),
        PitanjeAnketa("Pitanje 2f", "Anketa 6"),
        PitanjeAnketa("Pitanje 3f", "Anketa 6"),
        PitanjeAnketa("Pitanje 4f", "Anketa 6"),
        PitanjeAnketa("Pitanje 5f", "Anketa 6"),

        PitanjeAnketa("Pitanje 1g", "Anketa 7"),
        PitanjeAnketa("Pitanje 2g", "Anketa 7"),
        PitanjeAnketa("Pitanje 3g", "Anketa 7"),
        PitanjeAnketa("Pitanje 4g", "Anketa 7"),
        PitanjeAnketa("Pitanje 5g", "Anketa 7"),

        PitanjeAnketa("Pitanje 1h", "Anketa 8"),
        PitanjeAnketa("Pitanje 2h", "Anketa 8"),
        PitanjeAnketa("Pitanje 3h", "Anketa 8"),
        PitanjeAnketa("Pitanje 4h", "Anketa 8"),
        PitanjeAnketa("Pitanje 5h", "Anketa 8"),

        PitanjeAnketa("Pitanje 1i", "Anketa 9"),
        PitanjeAnketa("Pitanje 2i", "Anketa 9"),
        PitanjeAnketa("Pitanje 3i", "Anketa 9"),
        PitanjeAnketa("Pitanje 4i", "Anketa 9"),
        PitanjeAnketa("Pitanje 5i", "Anketa 9"),

        PitanjeAnketa("Pitanje 1j", "Anketa 10"),
        PitanjeAnketa("Pitanje 2j", "Anketa 10"),
        PitanjeAnketa("Pitanje 3j", "Anketa 10"),
        PitanjeAnketa("Pitanje 4j", "Anketa 10"),
        PitanjeAnketa("Pitanje 5j", "Anketa 10")
    )
}
