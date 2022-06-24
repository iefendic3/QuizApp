package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.repositories.*
import ba.etf.rma22.projekat.viewmodel.AnketaDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class FragmentPitanje : Fragment() {
    private lateinit var tekst : TextView
    private lateinit var lista : ListView
    private lateinit var dugmeZaustavi : Button
    private val  listaOdgovora = arrayListOf<String>()
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var nazivAnkete: String
    private lateinit var tekstPitanja : String
    private lateinit var nazivPitanja : String
    private var anketaDetailsViewModel = AnketaDetailsViewModel()
    private var idPit = -1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.fragment_pitanje, container, false)
        arguments?.getString("nazivPitanja")?.let { nazivPitanja = it }
        tekst = view.findViewById(R.id.tekstPitanja)
        lista = view.findViewById(R.id.odgovoriLista)
        dugmeZaustavi = view.findViewById(R.id.dugmeZaustavi)
        arguments?.getString("mojString")?.let { tekstPitanja = it }
        tekst.text = tekstPitanja
        arguments?.getString("nazivAnkete")?.let { nazivAnkete = it }
        arguments?.getInt("idPitanja")?.let { idPit = it }
        trenutnoPitanjeId = idPit
        arguments?.getStringArrayList("mojiOdgovori")?.let { listaOdgovora.addAll(it) }
        //adapter = MyArrayAdapter(requireContext(), R.layout.odgovor, listaOdgovora, tekstPitanja)
        lista.adapter = adapter
        val cal: Calendar = Calendar.getInstance()
        val referentniDatum: Date = cal.time
        GlobalScope.launch(Dispatchers.IO){
            launch(Dispatchers.IO){
                anketaDetailsViewModel.getOdgovoriAnketa(trenutnaAnketa.id, onSuccess = ::onSuccess, onError = ::onError)
            }
            delay(700)
            val odg = anketaDetailsViewModel.odgovoriZaPitanje.value
            var temp = 0
            if(odg!=null) {
                for (odgovor in odg) {
                    if (odgovor.pitanjeId == idPit) {
                        temp = 1
                        break
                    }
                }
            }
            if(referentniDatum >= trenutnaAnketa.datumPocetak && trenutnaAnketaTaken.progres!=100 && temp==0) {
                lista.setOnItemClickListener { adapterView, view, i, l ->
                    opcija = i
                    trenutnoPitanjeId = idPit
                    adapter.notifyDataSetChanged()
                    lista.isEnabled = false
                }
            }
            else{
                lista.isEnabled = false
            }
        }
        dugmeZaustavi.setOnClickListener {
            val fragments = mutableListOf(
                FragmentAnkete.newInstance(),
                FragmentIstrazivanje.newInstance()
            )
            mojAdapter.refreshAll(fragments,0)
        }
        return view
    }

    companion object {
        fun newInstance(s: String, odgovori : ArrayList<String>, nazivAnkete : String, idPitanja : Int, nazivPitanja : String) = FragmentPitanje().apply {
            arguments = Bundle().apply {
                putString("mojString", s)
                putStringArrayList("mojiOdgovori", odgovori)
                putString("nazivAnkete", nazivAnkete)
                putInt("idPitanja", idPitanja)
                putString("nazivPitanja", nazivPitanja)
            }
        }
    }

    private fun onSuccess() {}

    private fun onError() {}
}