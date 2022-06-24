package ba.etf.rma22.projekat.data.repositories

import ba.etf.rma22.projekat.data.models.*
import ba.etf.rma22.projekat.view.ViewPagerAdapter

//var istrazivanjaKorisnika = mutableListOf(istrazivanja()[0])
//var anketeKorisnika = mutableListOf(ankete()[0])
//var grupeKorisnika = mutableListOf(grupe()[0])
var godina : Int = 0
lateinit var mojAdapter : ViewPagerAdapter
//lateinit var anketaPitanja : List<PitanjeAnketa>
lateinit var trenutnaAnketa : Anketa
var trenutnoIstrazivanje = ""
lateinit var trenutnoPitanje : Pitanje
var opcija = -1
var trenutnaPitanja = listOf<Pitanje>()
lateinit var trenutnaAnketaTaken : AnketaTaken
var trenutniProgres = -1
var trenutnoPitanjeId = -1
var trenutniOdgovori = mutableListOf<Odgovor>()
var sveAnkete = mutableListOf<MojaAnketa>()
var mojeAnkete = true