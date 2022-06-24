package ba.etf.rma22.projekat.view

import android.content.Context
import android.os.Looper
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.mojeAnkete
import ba.etf.rma22.projekat.data.repositories.sveAnkete
import ba.etf.rma22.projekat.viewmodel.AnketaDetailsViewModel
import kotlinx.coroutines.*
import java.util.*

private var poceta = false
private var progres = 0
private lateinit var date : Date

class AnketaListAdapter(
    private var ankete : List<Anketa>,
    private val onItemClicked : (anketa:Anketa) -> Unit
) : RecyclerView.Adapter<AnketaListAdapter.AnketaViewHolder>() {
    inner class AnketaViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val anketaTitle: TextView = view.findViewById(R.id.naslov)
        val statusAnkete: ImageView = view.findViewById(R.id.ikona)
        val progressZavrsetka: ProgressBar = view.findViewById(R.id.progresZavrsetka)
        val datum: TextView = view.findViewById(R.id.datum)
        val istrazivanje : TextView = view.findViewById(R.id.istrazivanje)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AnketaViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.anketa_list, viewGroup, false)
        return AnketaViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: AnketaViewHolder, position: Int) {
        viewHolder.anketaTitle.text = ankete[position].naziv
        val anketaDetailsViewModel = AnketaDetailsViewModel()
        GlobalScope.launch(Dispatchers.IO){
            var idIstrazivanje = 0
            if(mojeAnkete) {
                launch(Dispatchers.IO){
                    anketaDetailsViewModel.getUpisaneGrupe(onSuccess = ::onSuccess4, onError = ::onError)
                }
                delay(500)
                val grupeStudenta = anketaDetailsViewModel.grupeStudenta.value
                for (mojaAnketa in sveAnkete) {
                    for (grupa in grupeStudenta!!) {
                        if (mojaAnketa.id == ankete[position].id && mojaAnketa.idGrupe == grupa.id && mojaAnketa.idIstrazivanja == grupa.istrazivanjeId && !mojaAnketa.iskoristeno) {
                            mojaAnketa.iskoristeno = true
                            idIstrazivanje = grupa.istrazivanjeId
                            break
                        }
                    }
                    if (idIstrazivanje != 0) break
                }
                launch(Dispatchers.IO) {
                    anketaDetailsViewModel.getIstrazivanjeZaId(
                        idIstrazivanje,
                        onSuccess = ::onSuccess3,
                        onError = ::onError
                    )
                }
                delay(1000)
                if(idIstrazivanje!=0) {
                    viewHolder.istrazivanje.text =
                        anketaDetailsViewModel.istrazivanjeZaId.value!!.naziv
                }
                else{
                    viewHolder.istrazivanje.text = "IM1"
                }
            }
            else{
                launch(Dispatchers.IO){
                    anketaDetailsViewModel.getGrupeZaAnketu(ankete[position].id,onSuccess = ::onSuccess2, onError = ::onError)
                }
                delay(500)
                val grupe = anketaDetailsViewModel.grupeZaAnketu.value
                var lista = mutableListOf<String>()
/*                for(grupa in grupe!!) {
                    launch(Dispatchers.IO) {
                        anketaDetailsViewModel.getIstrazivanjeZaId(
                            grupa.istrazivanjeId,
                            onSuccess = ::onSuccess3,
                            onError = ::onError
                        )
                    }
                    delay(500)
                    lista.add(anketaDetailsViewModel.istrazivanjeZaId.value!!.naziv)
                }*/
                var temp = 0
                var tekst = ""
                val lista1 = lista.distinct()
                for(istr in lista1){
                    if(temp == 0){
                        tekst = tekst + istr
                        temp = 1
                    }
                    else{
                        tekst = tekst + "/" + istr
                    }
                }
//                viewHolder.istrazivanje.text = tekst
            }
            launch(Dispatchers.IO){
                anketaDetailsViewModel.getPoceteAnketeAdapter(position, onSuccess = ::onSuccess, onError = ::onError)
            }
            delay(1000)
            if(position == ankete.size-1){
                for(mojaAnketa in sveAnkete){
                    mojaAnketa.iskoristeno = false
                }
            }
            if(position == ankete.size-1 && !mojeAnkete){
                mojeAnkete = true
            }
            val anketeTaken = anketaDetailsViewModel.anketeTakenAdapter.value
            if(anketeTaken!=null) {
                for (anketaTaken in anketeTaken) {
                    if (anketaTaken.AnketumId == ankete[position].id) {
                        poceta = true
                        progres = anketaTaken.progres
                        date = anketaTaken.datumRada
                        break
                    }
                }
            }
            if(poceta && progres!=0){
                if(progres >= 0 && progres<=9){
                    progres = 0
                }
                else if(progres>=10 && progres<=29){
                    progres=20
                }
                else if(progres>=30 && progres<=49){
                    progres=40
                }
                else if(progres>=50 && progres<=69){
                    progres=60
                }
                else if(progres>=70 && progres<=89){
                    progres = 80
                }
                else if(progres>=90 && progres<=100){
                    progres = 100
                }
            }
            //val refresh = Handler(Looper.getMainLooper())
            //refresh.post {
                viewHolder.progressZavrsetka.setProgress(progres)
                val context : Context = viewHolder.statusAnkete.getContext()

                var cal: Calendar = Calendar.getInstance()
                val referentniDatum: Date = cal.time

                if(referentniDatum.compareTo(ankete[position].datumPocetak) <0){
                    val id : Int = context.getResources().getIdentifier("zuta","drawable",context.getPackageName())
                    viewHolder.statusAnkete.setImageResource(id)
                    cal.setTime(ankete[position].datumPocetak)
                    viewHolder.datum.text = "Vrijeme aktiviranja: " + cal.get(Calendar.DAY_OF_MONTH) + "."+ cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR)
                }
                else if(referentniDatum.compareTo(ankete[position].datumPocetak) >=0
                    && (ankete[position].datumKraj !=null && referentniDatum.compareTo(ankete[position].datumKraj) >0 || progres==100)){
                    val id : Int = context.getResources().getIdentifier("plava","drawable",context.getPackageName())
                    withContext(Dispatchers.Main) {
                        viewHolder.statusAnkete.setImageResource(id)
                        val datum: Date = date
                        cal.setTime(datum)
                        viewHolder.datum.text =
                            "Anketa urađena: " + cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(
                                Calendar.MONTH
                            ) + "." + cal.get(Calendar.YEAR)
                    }
                }
                else if(referentniDatum.compareTo(ankete[position].datumPocetak) >=0 && progres<100){
                    val id : Int = context.getResources().getIdentifier("zelena","drawable",context.getPackageName())
                    withContext(Dispatchers.Main) {
                        viewHolder.statusAnkete.setImageResource(id)
                        //cal.setTime(ankete[position].datumKraj)
                        //viewHolder.datum.text = "Vrijeme zatvaranja: " + cal.get(Calendar.DAY_OF_MONTH) + "."+ cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR)
                        viewHolder.datum.text = "Vrijeme zatvaranja: "
                    }
                }
                else if(ankete[position].datumKraj!=null && referentniDatum.compareTo(ankete[position].datumKraj) >0){
                    val id : Int = context.getResources().getIdentifier("crvena","drawable",context.getPackageName())
                    viewHolder.statusAnkete.setImageResource(id)
                    cal.setTime(ankete[position].datumKraj)
                    viewHolder.datum.text = "Anketa zatvorena: " + cal.get(Calendar.DAY_OF_MONTH) + "."+ cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR)
                }
            withContext(Dispatchers.Main) {
                viewHolder.itemView.setOnClickListener { onItemClicked(ankete[position]) }
            }
                poceta = false
                progres = 0
            //}
        }
        GlobalScope.launch(Dispatchers.IO){

        }
    }
    fun updateAnkete(ankete: List<Anketa>) {
        this.ankete = ankete
        notifyDataSetChanged()
    }
    override fun getItemCount() = ankete.size

    private fun onSuccess(position : Int, poceteAnkete : List<AnketaTaken>){

    }

    private fun onSuccess2(listaGrupa : List<Grupa>){

    }

    private fun onSuccess3(istrazivanje: Istrazivanje){

    }

    private fun onSuccess4() {}
    private fun onError(){}
}