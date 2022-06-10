package ba.etf.rma22.projekat

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Pair
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.data.models.Anketa
import ba.etf.rma22.projekat.data.models.AnketaListViewModel
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository


class FragmentAnkete : Fragment() {

    private lateinit var listaAnketa: RecyclerView
    private lateinit var listaAnketaAdapter: AnketaListAdapter
    private var anketaListViewModel =  AnketaListViewModel()

    fun onSuccess(ankete:List<Anketa>){
        val toast = Toast.makeText(context, "Ankete pronađene", Toast.LENGTH_SHORT)
        toast.show()
        listaAnketaAdapter.updateAnkete(ankete)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Error", Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.ankete_fragment, container, false)
        listaAnketa = view.findViewById(R.id.listaAnketa)

        listaAnketa.layoutManager = GridLayoutManager(
            activity,
            2
        )
        listaAnketaAdapter = AnketaListAdapter(listOf()) {anketa -> showPitanja(anketa)}
        listaAnketa.adapter = listaAnketaAdapter

        var lista = anketaListViewModel.getAnkete()
        listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))



        //Spinner
        val spinner: Spinner = view.findViewById(R.id.filterAnketa)

        ArrayAdapter(
            activity as Context,
            android.R.layout.simple_spinner_item,
            resources.getStringArray(R.array.spinner_array)
        ).also { adapter ->

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            spinner.adapter = adapter

        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = parent?.getItemAtPosition(position).toString()
                if (selectedItem == "Sve ankete") {
                     anketaListViewModel.getAll(
                        onSuccess = ::onSuccess,
                        onError = ::onError
                    )
                    //listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))
                }
               /* else if(selectedItem=="Urađene ankete"){
                    lista = AnketaRepository.getDone()
                    listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))
                }
                else if(selectedItem=="Buduće ankete"){
                    lista = AnketaRepository.getFuture()
                    listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))
                }
                else if(selectedItem=="Prošle ankete"){
                    lista = AnketaRepository.getNotTaken()
                    listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))
                }*/
                else {
                    anketaListViewModel.getUpisane(
                        onSuccess = ::onSuccess,
                        onError = ::onError
                    )
//                    lista = AnketaRepository.getMyAnkete()
//                    listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))
                }
            }

        }

        return view
    }
    private fun showPitanja(anketa: Anketa){
        /*if(AnketaRepository.getMyAnkete().contains(anketa)) {
            var pitanja = PitanjeAnketaRepository.getPitanja(anketa.naziv, anketa.nazivIstrazivanja)


            var listaPitanja = listOf<Fragment>()

            val result = anketa.naziv
            val result2 = anketa.nazivIstrazivanja
            setFragmentResult("nazivi", bundleOf("data" to result, "data2" to result2))

            for (i in pitanja) {
                var listaOdgovora = ArrayList<String>()
                listaOdgovora.addAll(i.opcije)

                listaPitanja = listaPitanja + FragmentPitanje.newInstance(
                    i.tekst,
                    listaOdgovora, anketa.naziv, anketa.nazivIstrazivanja
                )
            }
            listaPitanja = listaPitanja + FragmentPredaj()
            adapter.addFragments(listaPitanja)
        }*/
    }
    override fun onResume() {
        super.onResume()
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.refreshFragment(1, FragmentIstrazivanje())
        }, 100)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentAnkete().apply {

            }
    }
}