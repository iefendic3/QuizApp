package ba.etf.rma22.projekat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener


class FragmentPoruka : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            var view = inflater.inflate(R.layout.poruka_fragment, container, false)
            setFragmentResultListener("requestKey") { key, bundle ->
                // Any type can be passed via to the bundle
                val result = bundle.getString("data")
                val result2 = bundle.getString("data2")
                // Do something with the result...
                var poruka = view.findViewById<TextView>(R.id.tvPoruka)

                poruka.text = "Uspješno ste upisani u grupu "+result+" istraživanja "+result2+"!"
            }
            setFragmentResultListener("nazivi"){ key, bundle ->
                val result = bundle.getString("data")
                val result2 = bundle.getString("data2")
                var poruka = view.findViewById<TextView>(R.id.tvPoruka)
                poruka.text = "Završili ste anketu "+result+" u okviru istraživanja "+ result2
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
         * @return A new instance of fragment FragmentPoruka.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPoruka().apply {

            }
    }
}