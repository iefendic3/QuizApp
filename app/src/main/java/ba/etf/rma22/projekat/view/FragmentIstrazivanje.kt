package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.models.Grupa
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.*
import ba.etf.rma22.projekat.viewmodel.AnketaDetailsViewModel

class FragmentIstrazivanje : Fragment() {

    private lateinit var spinnerGodina : Spinner
    private lateinit var spinnerIstrazivanje : Spinner
    private lateinit var spinnerGrupa : Spinner
    private lateinit var dugme : Button
    private var anketaDetailsViewModel = AnketaDetailsViewModel()
    private var trenutnaGodina = 0
    private var trenutnaIstrazivanja = mutableListOf<Istrazivanje>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.istrazivanje_fragment, container, false)
        spinnerGodina = view.findViewById(R.id.odabirGodina)
        spinnerIstrazivanje = view.findViewById(R.id.odabirIstrazivanja)
        spinnerGrupa = view.findViewById(R.id.odabirGrupa)
        dugme = view.findViewById(R.id.dodajIstrazivanjeDugme)
        dugme.isEnabled = false
        val arrayGodine = arrayOf(
            "1","2","3","4","5"
        )
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, arrayGodine
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGodina.adapter=adapter
        spinnerGodina.setSelection(godina)
        var listaIstrazivanja = mutableListOf<String>()
        val adapter2 = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, listaIstrazivanja
        )
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerIstrazivanje.adapter=adapter2
        var listaGrupa = mutableListOf<String>()
        val adapter3 = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item, listaGrupa
        )
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGrupa.adapter=adapter3
        anketaDetailsViewModel.getIstrazivanja(
            onSuccess = ::onSuccess,
            onError = ::onError
        )
        spinnerGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                trenutnaGodina = p2+1
                godina = p2
                anketaDetailsViewModel.istrazivanja.observe(requireActivity(), Observer { id ->
                    listaIstrazivanja.clear()
                    if(id!=null){
                        listaIstrazivanja.add(" ")
                        for(istrazivanje in id){
                            if(istrazivanje.godina==p2+1){
                                trenutnaIstrazivanja.add(istrazivanje)
                                listaIstrazivanja.add(istrazivanje.naziv)
                            }
                        }
                    }
                    adapter2.notifyDataSetChanged()
                    spinnerIstrazivanje.setSelection(0)
                })
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        spinnerIstrazivanje.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var nazivIstrazivanja = p0?.getItemAtPosition(p2).toString()
                var trenutniId = 0
                anketaDetailsViewModel.istrazivanja.observe(requireActivity(), Observer {
                    if (it != null) {
                        for (istrazivanje in it) {
                            if (istrazivanje.naziv == nazivIstrazivanja) {
                                trenutniId = istrazivanje.id
                                break
                            }
                        }
                    }
                })
                anketaDetailsViewModel.getGrupeZaIstrazivanje(trenutniId, onSuccess = ::onSuccess3, onError = ::onError)
                anketaDetailsViewModel.getUpisaneGrupe(onSuccess = ::onSuccess5, onError = ::onError)
                anketaDetailsViewModel.grupeZaIstrazivanje.observe(requireActivity(), Observer {
                    if(it!=null){
                        listaGrupa.clear()
                        for(grupa in it){
                            listaGrupa.add(grupa.naziv)
                        }
                    }
                    val lista = mutableListOf<String>()
                    for(grupa in listaGrupa){
                        for(grp in anketaDetailsViewModel.grupeStudenta.value!!){
                            if(grupa == grp.naziv){
                                lista.add(grupa)
                            }
                        }
                    }
                    listaGrupa.removeAll(lista)
                    adapter3.notifyDataSetChanged()
                    dugme.isEnabled = listaGrupa.size != 0
                    trenutnaIstrazivanja.clear()
                })
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }
        dugme.setOnClickListener {
            if(spinnerGodina.selectedItem!=null && spinnerIstrazivanje.selectedItem!=null && spinnerGrupa.selectedItem!=null){
                anketaDetailsViewModel.getGrupeFragmentIst(spinnerGrupa.selectedItem.toString(), onSuccess = ::onSuccess4, onError = ::onError)
                val tekst = "Uspješno ste upisani u grupu " + spinnerGrupa.selectedItem.toString() + " istraživanja " + spinnerIstrazivanje.selectedItem.toString() + "!"
                mojAdapter.refreshFragment(1, FragmentPoruka.newInstance(tekst))
            }
        }
        return view
    }

    companion object {
        fun newInstance(): FragmentIstrazivanje = FragmentIstrazivanje()
    }

    private fun onSuccess(istrazivanja : List<Istrazivanje>){

    }

    private fun onSuccess3(listGrupa : List<Grupa>){

    }

    private fun onSuccess4(nazivGrupe : String, listaGrupa: List<Grupa>){
        for(grupa in listaGrupa){
            if(grupa.naziv==nazivGrupe){
                anketaDetailsViewModel.upisiUGrupu(grupa.id, onSuccess = ::onSuccess5, onError = ::onError)
            }
        }
    }

    private fun onSuccess5(){}
    private fun onError(){}
}