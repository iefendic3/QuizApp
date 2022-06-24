//package ba.etf.rma22.projekat.view
//
//import android.content.Context
//import android.graphics.Color
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ArrayAdapter
//import android.widget.TextView
//import android.widget.Toast
//import androidx.annotation.LayoutRes
//import ba.etf.rma22.projekat.R
//import ba.etf.rma22.projekat.data.models.Odgovor
//import ba.etf.rma22.projekat.data.repositories.*
//import ba.etf.rma22.projekat.viewmodel.AnketaDetailsViewModel
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//private val odgovori = mutableListOf<Odgovor>()
//
//class MyArrayAdapter(context: Context, @LayoutRes private val layoutResource: Int, private val elements: ArrayList<String>, private val tekst : String):
//    ArrayAdapter<String>(context, layoutResource, elements) {
//    override fun getView(position: Int, newView: View?, parent: ViewGroup): View {
//        var view = newView
//        view = LayoutInflater.from(context).inflate(R.layout.odgovor, parent, false)
//        val textView = view.findViewById<TextView>(R.id.odgovorItem)
//        val element = elements.get(position)
//        textView.text=element
//        val anketaDetailsViewModel = AnketaDetailsViewModel()
//        if(opcija==-1){
//            GlobalScope.launch(Dispatchers.Main) {
//                launch {
//                    anketaDetailsViewModel.getOdgovoriAnketaAdapter(
//                        trenutnaAnketa.id,
//                        onSuccess = ::onSuccess,
//                        onError = ::onError
//                    )
//                }
//                delay(700)
//                val odg = anketaDetailsViewModel.odgovori.value
//                if(odg!=null) {
//                    var temp = false
//                    for (odgovor in odg) {
//                        for(pitanje in trenutnaPitanja){
//                            if(tekst == pitanje.tekstPitanja && odgovor.pitanjeId == pitanje.id && odgovor.anketaTakenId == trenutnaAnketaTaken.id && odgovor.odgovoreno == position){
//                                textView.setTextColor(Color.parseColor("#0000FF"))
//                                temp = true
//                                break
//                            }
//                        }
//                        if(temp) break
//                    }
//                }
//            }
//        }
//        else if(opcija!=-1 && opcija==position){
//            GlobalScope.launch(Dispatchers.IO){
//                launch(Dispatchers.IO){
//                    anketaDetailsViewModel.postaviOdgovorAnketa(trenutnaAnketaTaken.id, trenutnoPitanjeId, opcija, onSuccess = ::onSuccess2, onError = ::onError2)
//                }
//                delay(500)
//                if(anketaDetailsViewModel.prog>0){
//                    textView.setTextColor(Color.parseColor("#0000FF"))
//                }
//                opcija = -1
//            }
//
//        }
//        return view
//    }
//
//    private fun onSuccess(listaOdgovora : List<Odgovor>){
//
//    }
//    private fun onSuccess2(prog : Int){
//        trenutniProgres=prog
//    }
//    private fun onError() {}
//
//    private fun onError2(prog : Int) {
//        if(prog == -1){
//            val toast = Toast.makeText(context, "Već je odgovoreno na ovo pitanje", Toast.LENGTH_SHORT)
//            toast.show()
//        }
//    }
//}