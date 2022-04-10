package ba.etf.rma22.projekat

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ba.etf.rma22.projekat.data.models.Istrazivanje
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import ba.etf.rma22.projekat.data.repositories.GrupaRepository
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeRepository


class UpisIstrazivanje : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.upis_istrazivanje)


        val odabirGodina = findViewById<Spinner>(R.id.odabirGodina)
        val odabirIstrazivanja = findViewById<Spinner>(R.id.odabirIstrazivanja)
        val odabirGrupa = findViewById<Spinner>(R.id.odabirGrupa)
        val upisiMe = findViewById<Button>(R.id.dodajIstrazivanjeDugme)
        ArrayAdapter.createFromResource(
            this,
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
                        this@UpisIstrazivanje,
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
                        this@UpisIstrazivanje,
                        android.R.layout.simple_spinner_dropdown_item,
                        lista
                    )
                    odabirGrupa.adapter = adapter
                }

            }

        upisiMe.setOnClickListener {
            var lista = AnketaRepository.getAll()

            for(i in lista) {

                if (odabirIstrazivanja.selectedItem ==i.nazivIstrazivanja && odabirGrupa.selectedItem == i.nazivGrupe
                    ){


                    if(IstrazivanjeRepository.dodajUpisani(
                        Istrazivanje(odabirIstrazivanja.selectedItem.toString(),
                        Integer.parseInt(odabirGodina.selectedItem.toString()))
                    ) ){
                        AnketaRepository.addMyAnketu(i)
                    }
                    else{
                        Toast.makeText(applicationContext,"Već ste upisani u grupu za ovo istraživanje", Toast.LENGTH_LONG).show()
                    }

                }
                val intent1 = Intent(this@UpisIstrazivanje, MainActivity::class.java)
                intent1.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK or
                        Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent1)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

        }

        }

    }



