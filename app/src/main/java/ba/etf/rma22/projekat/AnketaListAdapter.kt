package ba.etf.rma22.projekat

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AnketaListAdapter(
    private var ankete: List<Anketa>,
    private val onItemClicked: (anketa:Anketa) -> Unit
) : RecyclerView.Adapter<AnketaListAdapter.AnketaViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnketaViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.anketa_list, parent, false)
        return AnketaViewHolder(view)
    }
    override fun getItemCount(): Int = ankete.size
    override fun onBindViewHolder(holder: AnketaViewHolder, position: Int) {
        holder.nazivAnkete.text=ankete[position].naziv
        holder.nazivIstrazivanja.text=ankete[position].nazivIstrazivanja
        holder.progressBar.setProgress((ankete[position].progres * 100.0f).toInt())
        holder.progressBar.getProgressDrawable().setColorFilter(
            Color.BLUE, android.graphics.PorterDuff.Mode.SRC_IN)
        holder.itemView.setOnClickListener{
            onItemClicked(ankete[position])
        }
        /*holder.itemView.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                val activity = v!!.context as AppCompatActivity
                val fragmentPitanje = FragmentPitanje()
                val fragmentPredaj = FragmentPredaj()
                val pitanja = PitanjeAnketaRepository.getPitanja("Anketa 1","Istrazivanje A")
                var brojac = 0
                for(i in pitanja){
                    if(brojac == 0)
                    adapter.refreshFragment(brojac,FragmentPitanje())
                    else
                    adapter.add(brojac,FragmentPitanje())
                    brojac++
                }

                   adapter.refreshFragment(adapter.itemCount - 1, fragmentPredaj)

        }})*/

        //Pronalazimo id drawable elementa na osnovu naziva žanra
        val context: Context = holder.statusAnkete.getContext()
        var id: Int = 0



        val refDate = Date()
        val calendar = Calendar.getInstance()
        calendar.time = refDate

        if(refDate.compareTo(ankete[position].datumPocetak) < 0){
            id= context.getResources().getIdentifier("zuta","drawable",context.getPackageName())
            holder.statusAnkete.setImageResource(id)
            calendar.time = ankete[position].datumPocetak
            holder.datum.text = "Datum početka ankete: "+calendar.get(Calendar.DAY_OF_MONTH)+". "+calendar.get(Calendar.MONTH)+". "+calendar.get(Calendar.YEAR)
        }
        else if(refDate.compareTo(ankete[position].datumPocetak)>=0
            && refDate.compareTo(ankete[position].datumKraj)<0
            && ankete[position].datumRada==null){
            id= context.getResources().getIdentifier("zelena","drawable",context.getPackageName())
            holder.statusAnkete.setImageResource(id)
            calendar.time = ankete[position].datumKraj
            holder.datum.text = "Datum kraja ankete: "+calendar.get(Calendar.DAY_OF_MONTH)+". "+calendar.get(Calendar.MONTH)+". "+calendar.get(Calendar.YEAR)

        }
        else if(refDate.compareTo(ankete[position].datumPocetak)>=0
            && ankete[position].datumRada!=null && ankete[position].progres==1f){
            id= context.getResources().getIdentifier("plava","drawable",context.getPackageName())
            holder.statusAnkete.setImageResource(id)
            calendar.time= ankete[position].datumRada
            holder.datum.text = "Datum kada je anketa urađena: "+calendar.get(Calendar.DAY_OF_MONTH)+". "+calendar.get(Calendar.MONTH)+". "+calendar.get(Calendar.YEAR)

        }
        else if((refDate.compareTo(ankete[position].datumKraj) >0
            && ankete[position].datumRada == null) || (refDate.compareTo(ankete[position].datumKraj) >0
                    && ankete[position].datumRada != null && ankete[position].progres<1f)){
            id= context.getResources().getIdentifier("crvena","drawable",context.getPackageName())
            holder.statusAnkete.setImageResource(id)
            calendar.time = ankete[position].datumKraj
            holder.datum.text = "Datum kraja ankete: "+calendar.get(Calendar.DAY_OF_MONTH)+". "+calendar.get(Calendar.MONTH)+". "+calendar.get(Calendar.YEAR)

        }
    }

    fun updateAnkete(ankete: List<Anketa>) {
        this.ankete = ankete
        notifyDataSetChanged()
    }

    override fun toString(): String {
        //simple date formatter
        val dateFormatter = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

        //return the formatted date string
        return dateFormatter.format(this)
    }

    inner class AnketaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nazivAnkete: TextView = itemView.findViewById(R.id.naslov)
        val statusAnkete: ImageView = itemView.findViewById(R.id.ikona)
        val nazivIstrazivanja: TextView = itemView.findViewById(R.id.istrazivanje)
        val progressBar: ProgressBar = itemView.findViewById(R.id.progresZavrsetka)
        val datum: TextView = itemView.findViewById(R.id.datum)

    }
}