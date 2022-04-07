package ba.etf.rma22.projekat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ba.etf.rma22.projekat.data.models.AnketaListViewModel
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private lateinit var listaAnketa: RecyclerView
    private lateinit var listaAnketaAdapter: AnketaListAdapter
    private var anketaListViewModel =  AnketaListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val dodajDugme = findViewById<FloatingActionButton>(R.id.upisDugme)
        listaAnketa = findViewById(R.id.listaAnketa)
        listaAnketa.layoutManager = GridLayoutManager(
            this,
            2
        )
        listaAnketaAdapter = AnketaListAdapter(listOf())
        listaAnketa.adapter = listaAnketaAdapter

        var lista = anketaListViewModel.getAnkete()
        listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))

        //Dugme
        dodajDugme.setOnClickListener{

            val intent = Intent(this, UpisIstrazivanje::class.java)
            startActivity(intent)
        }



        //Spinner
        val spinner: Spinner = findViewById(R.id.filterAnketa)

        ArrayAdapter.createFromResource(
            this,
            R.array.spinner_array,
            android.R.layout.simple_spinner_item
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
                    lista = AnketaRepository.getAll()
                    listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))
                }
                else if(selectedItem=="Urađene ankete"){
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
                }
                else {
                    lista = AnketaRepository.getMyAnkete()
                    listaAnketaAdapter.updateAnkete(lista.sortedWith(compareBy { it.component3() }))
                }
            }

        }


    }




}