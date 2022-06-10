package ba.etf.rma22.projekat

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class FragmentIstrazivanje : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.istrazivanje_fragment, container, false)
        val odabirGodina = view.findViewById<Spinner>(R.id.odabirGodina)
        val odabirIstrazivanja = view.findViewById<Spinner>(R.id.odabirIstrazivanja)
        val odabirGrupa = view.findViewById<Spinner>(R.id.odabirGrupa)
        val upisiMe = view.findViewById<Button>(R.id.dodajIstrazivanjeDugme)
        ArrayAdapter.createFromResource(
            activity as Context,
            R.array.godine_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            odabirGodina.adapter = adapter
        }



        odabirGodina.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = odabirGodina.getSelectedItem().toString()
                var lista = listOf<String>()
                var lista2 = listOf<Istrazivanje>()

                if(selectedItem != "Odabir godine") {
                    upisiMe.isEnabled = true
                    upisiMe.isClickable = true
                    lista = listOf<String>()
                    lista2 =
                        IstrazivanjeRepository.getIstrazivanjeByGodina(Integer.parseInt(selectedItem))
                    for (i in lista2) {

                        lista = lista + i.naziv
                    }
                    val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                        activity as Context,
                        android.R.layout.simple_spinner_dropdown_item,
                        lista
                    )
                    odabirIstrazivanja.adapter = adapter
                }
                else{
                    lista = listOf<String>()
                    lista2 = listOf<Istrazivanje>()
                    upisiMe.isEnabled = false
                    upisiMe.isClickable = false
                    odabirIstrazivanja.adapter = null
                    odabirGrupa.adapter = null
                }
            }

        }
        odabirIstrazivanja.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = odabirIstrazivanja.getSelectedItem().toString()
                var lista = listOf<String>()
                var lista2 = GrupaRepository.getGroupsByIstrazivanje(selectedItem)

                for (i in lista2) {

                    lista = lista + i.naziv
                }
                val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                    activity as Context,
                    android.R.layout.simple_spinner_dropdown_item,
                    lista
                )
                odabirGrupa.adapter = adapter
            }

        }

        upisiMe.setOnClickListener {
            //var lista = AnketaRepository.getAll()

            /*for(i in lista) {

                if (odabirIstrazivanja.selectedItem ==i.nazivIstrazivanja && odabirGrupa.selectedItem == i.nazivGrupe
                ){


                    if(IstrazivanjeRepository.dodajUpisani(
                            Istrazivanje(odabirIstrazivanja.selectedItem.toString(),
                                Integer.parseInt(odabirGodina.selectedItem.toString()))
                        ) ){
                        AnketaRepository.addMyAnketu(i)
                        adapter.refreshFragment(1, FragmentPoruka())
                        adapter.refreshFragment(0,FragmentAnkete())

                        val result = odabirGrupa.selectedItem.toString()
                        val result2 = odabirIstrazivanja.selectedItem.toString()
                        setFragmentResult("requestKey", bundleOf("data" to result, "data2" to result2))
                    }
                    else{
                        Toast.makeText(activity,"Već ste upisani u grupu za ovo istraživanje", Toast.LENGTH_LONG).show()
                    }

                }


               /* val intent1 = Intent(activity, FragmentAnkete::class.java)
                intent1.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent1)
                val intent = Intent(activity, FragmentAnkete::class.java)
                startActivity(intent)*/
            }*/

        }
        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment2.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentIstrazivanje().apply {

            }
    }
}