package ba.etf.rma22.projekat.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import ba.etf.rma22.projekat.R
import ba.etf.rma22.projekat.data.repositories.mojAdapter

class FragmentPoruka : Fragment() {

    private lateinit var poruka : TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view =  inflater.inflate(R.layout.poruka_fragment, container, false)
        poruka = view.findViewById(R.id.tvPoruka)
        arguments?.getString("tekst").let { poruka.text = it }

        return view
    }

    companion object {
        fun newInstance(tekst : String) = FragmentPoruka().apply {
            arguments = Bundle().apply {
                putString("tekst", tekst)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        Handler(Looper.getMainLooper()).postDelayed({
            mojAdapter.refreshFragment(1, FragmentIstrazivanje.newInstance())
        }, 100)
    }
}