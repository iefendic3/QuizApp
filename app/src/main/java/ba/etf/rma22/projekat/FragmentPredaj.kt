package ba.etf.rma22.projekat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class FragmentPredaj : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_predaj, container, false)
        val predaj = view.findViewById<Button>(R.id.dugmePredaj)

        predaj.setOnClickListener {
            adapter.addFragments(listOf(FragmentAnkete(),FragmentPoruka()))
            viewPager.currentItem = 1
        }
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPredaj().apply {

            }
    }
}