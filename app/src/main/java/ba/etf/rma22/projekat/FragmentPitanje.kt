package ba.etf.rma22.projekat

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import ba.etf.rma22.projekat.data.repositories.PitanjeAnketaRepository


class FragmentPitanje : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pitanje, container, false)
        val pitanja = PitanjeAnketaRepository.getPitanja("Anketa 1", "Istrazivanje A")


        var tekstPitanja = view.findViewById<TextView>(R.id.tekstPitanja)
        arguments?.getString("mojString").let{tekstPitanja.text = it}

        val zaustavi = view.findViewById<Button>(R.id.dugmeZaustavi)

        zaustavi.setOnClickListener {
            adapter.addFragments(listOf(FragmentAnkete(),FragmentIstrazivanje()))
            viewPager.currentItem = 0
        }
        var listView = view.findViewById<ListView>(R.id.odgovoriLista)
        var odgovori = arrayListOf<String>()
        arguments?.getStringArrayList("odgovori")?.let{odgovori.addAll(it)}

        val adapterList = OdgovorAdapter(requireContext(),R.layout.odgovor,odgovori)
        listView.adapter = adapterList

      /*  listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val selectedItemText = parent.getItemAtPosition(position)
                (selectedItemText).setTextColor(Color.parseColor("#0000FF"))
            }*/
        return view
    }

    companion object {

        fun newInstance(string: String, odgovori: ArrayList<String>, nazivAnkete: String, nazivIstrazivanja: String):FragmentPitanje{
            val args = Bundle()
            args.putString("mojString",string)
            args.putStringArrayList("odgovori",odgovori)
            args.putString("nazivAnkete",nazivAnkete)
            args.putString("nazivIstrazivanja",nazivIstrazivanja)
            val fragment = FragmentPitanje()
            fragment.setArguments(args)
            return fragment
        }
    }
}