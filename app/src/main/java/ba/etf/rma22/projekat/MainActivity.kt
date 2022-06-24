package ba.etf.rma22.projekat

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.repositories.*
import ba.etf.rma22.projekat.view.*
import ba.etf.rma22.projekat.viewmodel.AnketaDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

lateinit var viewPager: ViewPager2
class MainActivity : AppCompatActivity() {

    private val anketaDetailsViewModel = AnketaDetailsViewModel()
    private var brojPitanja : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AccountRepository.setContext(applicationContext)
        TakeAnketaRepository.setContext(applicationContext)
        AnketaRepository.setContext(applicationContext)
        IstrazivanjeIGrupaRepository.setContext(applicationContext)
        OdgovorRepository.setContext(applicationContext)
        PitanjeAnketaRepository.setContext(applicationContext)

        val action: String? = intent?.action
        val data: Uri? = intent?.data

        val user : String? = intent.extras?.getString("payload")
        if (user != null) {
            anketaDetailsViewModel.upisi(applicationContext ,user, onSuccess = ::onSuccess, onError = ::onError )
        }

        GlobalScope.launch(Dispatchers.IO){
            launch(Dispatchers.IO){
                anketaDetailsViewModel.getSveAnkete(onSuccess = ::onSuccess, onError = ::onError)
            }
        }
        //anketaPitanja = pitanjaAnketa()

        viewPager = findViewById(R.id.pager)

        val fragments = mutableListOf(
            FragmentAnkete.newInstance(),
            FragmentIstrazivanje.newInstance()
        )

        mojAdapter = ViewPagerAdapter(fragments, supportFragmentManager, lifecycle)
        viewPager.adapter = mojAdapter
    }

    private fun onSuccess() {}
    private fun onError() {}
}