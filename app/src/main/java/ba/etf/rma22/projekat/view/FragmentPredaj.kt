package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.*
import java.util.*
import kotlin.math.roundToInt

class FragmentPredaj : Fragment() {
    private lateinit var progres : TextView
    private lateinit var dugmePredaj : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_predaj, container, false)
        progres = view.findViewById(R.id.progresTekst)
        dugmePredaj = view.findViewById(R.id.dugmePredaj)
        arguments?.getInt("mojInt").let { progres.text = it.toString() + "%" }
        val tekst = "Završili ste anketu " + trenutnaAnketa + " u okviru istraživanja " + trenutnoIstrazivanje
        var cal: Calendar = Calendar.getInstance()
        val referentniDatum: Date = cal.time
        if(referentniDatum >= trenutnaAnketa.datumPocetak && trenutnaAnketaTaken.progres!=100) {
            dugmePredaj.setOnClickListener {
                val fragments = mutableListOf(
                    FragmentAnkete.newInstance(),
                    FragmentPoruka.newInstance(tekst)
                )
                mojAdapter.refreshAll(fragments,1)
            }
        }
        else{
            dugmePredaj.isEnabled = false
        }
        return view
    }

    companion object {
        fun newInstance(prog : Int, nazivAnkete : String, nazivIstrazivanja : String) = FragmentPredaj().apply {
            arguments = Bundle().apply {
                var temp = odrediPostotak(prog)
                putInt("mojInt", temp)
                putString("nazivIstrazivanja", nazivIstrazivanja)
                putString("nazivAnkete", nazivAnkete)
            }
        }
    }

    private fun odrediPostotak(x : Int) : Int {
        var temp = x
        if(temp >= 0 && temp<=9){
            temp = 0
        }
        else if(temp>=10 && temp<=29){
            temp=20
        }
        else if(temp>=30 && temp<=49){
            temp=40
        }
        else if(temp>=50 && temp<=69){
            temp=60
        }
        else if(temp>=70 && temp<=89){
            temp = 80
        }
        else if(temp>=90 && temp<=100){
            temp = 100
        }
        return temp
    }

    override fun onResume() {
        super.onResume()
        if(trenutniProgres!=-1) {
            progres.text = trenutniProgres.toString() + "%"
            trenutniProgres = -1
        }
    }
}