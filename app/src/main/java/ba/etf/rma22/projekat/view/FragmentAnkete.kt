package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.CheckNetworkConnection
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaTaken
import ba.etf.rma22.projekat.data.models.Pitanje
import ba.etf.rma22.projekat.data.repositories.*
import ba.etf.rma22.projekat.viewPager
import ba.etf.rma22.projekat.viewmodel.AnketaDetailsViewModel
import ba.etf.rma22.projekat.viewmodel.AnketaListViewModel
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.roundToInt

class FragmentAnkete : Fragment() {

    private lateinit var ankete: RecyclerView
    private lateinit var spinner: Spinner
    private lateinit var anketeAdapter: AnketaListAdapter
    private var anketaDetailsViewModel = AnketaDetailsViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.ankete_fragment, container, false)
        ankete = view.findViewById(R.id.listaAnketa)
        spinner = view.findViewById(R.id.filterAnketa)
        ankete.layoutManager = GridLayoutManager(
            activity,
            2
        )
        anketeAdapter = AnketaListAdapter(listOf()) {anketa -> anketaPitanja(anketa)}
        ankete.adapter = anketeAdapter
        if(provjeriKonekciju()) {
            anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess1, onError = ::onError)
        }
        else{
            anketaDetailsViewModel.getUpisaneDB(onSuccess = ::onSuccess1, onError = ::onError)
        }
        val arraySpinner = arrayOf(
            "Sve moje ankete", "Sve ankete", "Urađene ankete", "Buduće ankete", "Prošle ankete"
        )
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, arraySpinner
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (p2 == 0) {
                    anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess1, onError = ::onError)
                }
                else if (p2 == 1) {
                    anketaDetailsViewModel.getAll(onSuccess = ::onSuccess2, onError = ::onError)
                }
                else if (p2 == 2) {
                    anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess3, onError = ::onError)
                }
                else if (p2 == 3) {
                    anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess5, onError = ::onError)
                }
                else if (p2 == 4) {
                    anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess9, onError = ::onError)
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        return view
    }

    companion object {
        fun newInstance(): FragmentAnkete = FragmentAnkete()
    }

    private fun anketaPitanja(anketa : Anketa) {
        var cal: Calendar = Calendar.getInstance()
        val referentniDatum: Date = cal.time
        if(!spinner.selectedItem.equals("Sve ankete") && referentniDatum >= anketa.datumPocetak) {
            val fragments = mutableListOf<Fragment>()
            trenutnaAnketa = anketa
            GlobalScope.launch(Dispatchers.IO){
                launch(Dispatchers.IO){
                    anketaDetailsViewModel.getPitanjaFragment1(anketa.id, onSuccess = ::onSuccess6, onError = ::onError)
                }
                delay(500)
                val pitanja = anketaDetailsViewModel.pitanja.value
                trenutnaPitanja = pitanja!!
                for (pitanje in pitanja!!) {
                    val lista = ArrayList<String>()
                    lista.addAll(pitanje.opcije)
                    trenutnoPitanje = pitanje
                    val fragment = FragmentPitanje.newInstance(
                        pitanje.tekstPitanja,
                        lista,
                        anketa.naziv,
                        pitanje.id,
                        pitanje.naziv
                    )
                    fragments.add(fragment)
                }
                launch(Dispatchers.IO){
                    anketaDetailsViewModel.zapocniAnketu(anketa.id, onSuccess = ::onSuccess7, onError = ::onError)
                }
                delay(500)
                val anketaTaken = anketaDetailsViewModel.anketaTkn.value
                if(anketaTaken==null){
                    launch(Dispatchers.IO){
                        anketaDetailsViewModel.getPoceteAnkete(onSuccess = ::onSuccess8, onError = ::onError)
                    }
                    delay(500)
                    val anketeTaken = anketaDetailsViewModel.anketeTaken.value
                    for(anketatkn in anketeTaken!!){
                        if(anketatkn.AnketumId==anketa.id){
                            trenutnaAnketaTaken = anketatkn
                            val fragment = FragmentPredaj.newInstance(anketatkn.progres, anketa.naziv, "Istrazivanje")
                            fragments.add(fragment)
                            break
                        }
                    }
                }
                else {
                    trenutnaAnketaTaken = anketaTaken
                    val fragment = FragmentPredaj.newInstance(
                        anketaTaken.progres,
                        anketa.naziv,
                        "Istrazivanje"
                    )
                    fragments.add(fragment)
                }
                withContext(Dispatchers.Main){
                    viewPager.offscreenPageLimit = fragments.size
                    mojAdapter.refreshAll(fragments, 0)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        when {
            spinner.selectedItem.equals("Sve moje ankete") -> {
                anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess1, onError = ::onError)
            }
            spinner.selectedItem.equals("Sve ankete") -> {
                anketaDetailsViewModel.getAll(onSuccess = ::onSuccess2, onError = ::onError)
            }
            spinner.selectedItem.equals("Urađene ankete") -> {
                anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess3, onError = ::onError)
            }
            spinner.selectedItem.equals("Buduće ankete") -> {
                anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess5, onError = ::onError)
            }
            spinner.selectedItem.equals("Prošle ankete") -> {
                anketaDetailsViewModel.getUpisane(onSuccess = ::onSuccess9, onError = ::onError)
            }
        }
    }

    private fun onSuccess1(mojeAnkete : List<Anketa>){
        anketeAdapter.updateAnkete(mojeAnkete.sorted())
    }
    private fun onSuccess2(sveAnkete : List<Anketa>){
        mojeAnkete = false
        anketeAdapter.updateAnkete(sveAnkete.sorted())
    }
    private fun onSuccess3(mojeAnkete : List<Anketa>){
        anketaDetailsViewModel.getPoceteAnketeFragment1(mojeAnkete, onSuccess = ::onSuccess4, onError = ::onError2)
    }
    private fun onSuccess4(mojeUradjeneAnkete: List<Anketa>, poceteAnkete : List<AnketaTaken>){
        var lista = mutableListOf<Anketa>()
        for(anketaTaken in poceteAnkete){
            if(anketaTaken.progres==100){
                for(anketa in mojeUradjeneAnkete){
                    if(anketaTaken.AnketumId==anketa.id){
                        lista.add(anketa)
                        break
                    }
                }
            }
        }
        anketeAdapter.updateAnkete(lista.sorted())
    }
    private fun onSuccess5(mojeAnkete: List<Anketa>){
        var lista = mutableListOf<Anketa>()
        var cal: Calendar = Calendar.getInstance()
        val referentniDatum: Date = cal.time
        GlobalScope.launch(Dispatchers.IO){
            launch(Dispatchers.IO){
                anketaDetailsViewModel.getPoceteAnkete(onSuccess = ::onSuccess8, onError = ::onError)
            }
            delay(500)
            val anketeTaken = anketaDetailsViewModel.anketeTaken.value
            for(anketa in mojeAnkete){
                if(referentniDatum.compareTo(anketa.datumPocetak) <0){
                    lista.add(anketa)
                }
                else{
                    if(anketeTaken!=null) {
                        var temp = true
                        for (anketaTkn in anketeTaken){
                            if(anketaTkn.AnketumId==anketa.id && anketaTkn.progres==100 || anketa.datumKraj!=null && referentniDatum.compareTo(anketa.datumKraj) > 0){
                                temp = false
                                break
                            }
                        }
                        if(temp){
                            lista.add(anketa)
                        }
                    }
                    else{
                        lista.add(anketa)
                    }
                }
            }
            withContext(Dispatchers.Main) {
                anketeAdapter.updateAnkete(lista.sorted())
            }
        }
    }

    private fun onSuccess6(pitanja : List<Pitanje>){
        //trenutnaPitanja = pitanja
    }

    private fun onSuccess7(anketaTaken: AnketaTaken) {
        //trenutnaAnketaTaken = anketaTaken
    }

    private fun onSuccess8(){}

    private fun onSuccess9(mojeAnkete: List<Anketa>){
        anketaDetailsViewModel.getPoceteAnketeFragment1(mojeAnkete, onSuccess = ::onSuccess10, onError = ::onError)
    }

    private fun onSuccess10(mojeAnkete: List<Anketa>, poceteAnkete: List<AnketaTaken>){
        var lista = mutableListOf<Anketa>()
        var cal: Calendar = Calendar.getInstance()
        val referentniDatum: Date = cal.time
        for(anketa in mojeAnkete){
            var temp1 = 0
            var temp2 = 0
            for(anketaTaken in poceteAnkete){
                if(anketa.id == anketaTaken.AnketumId || anketa.datumKraj==null){
                    temp1 = 1
                }
                if(anketa.id == anketaTaken.AnketumId && anketaTaken.progres == 0 && anketa.datumKraj!=null && referentniDatum.compareTo(anketa.datumKraj) > 0){
                    temp2 = 1
                    break
                }
            }
            if(temp2 == 1 || temp1 == 0){
                lista.add(anketa)
            }
        }
        anketeAdapter.updateAnkete(lista.sorted())
    }

    private fun onError() {}

    private fun onError2() {
        val lista = listOf<Anketa>()
        anketeAdapter.updateAnkete(lista)
    }

    fun provjeriKonekciju() : Boolean{
        val checkNetworkConnection = CheckNetworkConnection(requireActivity().application)
        var temp = true
        checkNetworkConnection.observe(viewLifecycleOwner) { isConnected ->
            temp = isConnected
        }
        return temp
    }
}