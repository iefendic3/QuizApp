package ba.etf.rma22.projekat

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.LayoutRes

class OdgovorAdapter(
    con: Context, @LayoutRes private val layoutRes: Int, private val odgovori: ArrayList<String>
    )
    : ArrayAdapter<String>(con, layoutRes, odgovori) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {

        var v = LayoutInflater.from(context).inflate(R.layout.odgovor, parent, false)

        val odg = v.findViewById<TextView>(R.id.odgovorItem)
        odg.text = odgovori[position]


        return v
    }
}