package ba.etf.rma22.projekat
import ba.etf.rma22.projekat.data.models.Anketa
import java.util.*

fun ankete(): List<Anketa>{
    var cal: Calendar = Calendar.getInstance()
    cal.set(2022,4,4)
    var date1: Date = cal.time;
    cal.set(2022,4,5)
    var date2: Date = cal.time;
    cal.set(2022,4,5)
    var date3: Date = cal.time;

    cal.set(2022,3,20)
    var date4: Date = cal.time
    cal.set(2022,3,25)
    var date5: Date = cal.time

    cal.set(2022,5,5)
    var date6: Date = cal.time

    cal.set(2022,5,6)
    var date7: Date=cal.time

    cal.set(2022,3,18)
    var date8: Date=cal.time

    cal.set(2022,3,21)
    var date9: Date=cal.time

    return listOf(
        Anketa("Anketa 1","Istrazivanje A",
            date1,date2,null,5,"Grupa 1a",0f),
        Anketa("Anketa 2","Istrazivanje A",date4, date5, null,5,"Grupa 2a",0f)
        , Anketa("Anketa 3","Istrazivanje B",date4,date6,null,5,"Grupa 1b",0f)
        , Anketa("Anketa 4","Istrazivanje B",date4,date5,date4,5,"Grupa 2b",0.7f)
        , Anketa("Anketa 5","Istrazivanje C",date8,date7,date8,5,"Grupa 1c",1f)
        , Anketa("Anketa 6","Istrazivanje C",date9,date5,date9,5,"Grupa 2c",1f)
    )
}
fun mojeAnkete(): List<Anketa>{
    var cal: Calendar = Calendar.getInstance()
    cal.set(2022,3,25)
    var date5: Date = cal.time
    cal.set(2022,3,21)
    var date9: Date=cal.time
    return listOf(
        Anketa("Anketa 6","Istrazivanje C",date9,date5,date9,5,"Grupa 2c",1f)
    )
}

fun mojeIstrazivanje(): List<Istrazivanje>{
    return listOf(
        Istrazivanje("Istrazivanje C",3)
    )
}

fun istrazivanja(): List<Istrazivanje>{
    return listOf(
        Istrazivanje("Istrazivanje A",1),
        Istrazivanje("Istrazivanje B",2),
        Istrazivanje("Istrazivanje C",3),
        Istrazivanje("Istrazivanje D",4),
        Istrazivanje("Istrazivanje E",5)
    )
}
fun grupe(): List<Grupa>{
    return listOf(
        Grupa("Grupa 1a","Istrazivanje A"),
        Grupa("Grupa 2a","Istrazivanje A"),

        Grupa("Grupa 1b","Istrazivanje B"),
        Grupa("Grupa 2b","Istrazivanje B"),

        Grupa("Grupa 1c","Istrazivanje C"),
        Grupa("Grupa 2c","Istrazivanje C"),
    )
}