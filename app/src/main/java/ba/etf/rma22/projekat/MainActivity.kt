package ba.etf.rma22.projekat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ba.etf.rma22.projekat.data.repositories.ApiConfig
import ba.etf.rma22.projekat.data.repositories.IstrazivanjeIGrupaRepository


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.pager)

        val fragments: ArrayList<Fragment> = arrayListOf(
            FragmentAnkete(), FragmentIstrazivanje()
        )
        adapter = ViewPagerAdapter(supportFragmentManager, fragments, lifecycle)

        viewPager.adapter = adapter


    }


}


