package ba.etf.rma22.projekat

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import ba.etf.rma22.projekat.UtilTestClass.Companion.hasItemCount
import ba.etf.rma22.projekat.UtilTestClass.Companion.itemTest
import ba.etf.rma22.projekat.data.repositories.AnketaRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.hamcrest.CoreMatchers.`is` as Is


@RunWith(AndroidJUnit4::class)
class MySpirala2AndroidTest {

    @get:Rule
    val intentsTestRule = ActivityScenarioRule<MainActivity>(MainActivity::class.java)

    @Test
    fun postojiSveNaFragmentIstrazivanje() {

        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(0))
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToPosition(1))
        onView(withId(R.id.odabirGodina)).check(matches(isDisplayed()))
        onView(withId(R.id.odabirIstrazivanja)).check(matches(isDisplayed()))
        onView(withId(R.id.odabirGrupa)).check(matches(isDisplayed()))
    }

    @Test
    fun testZaustavi() {
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToFirst())
        onView(withId(R.id.filterAnketa)).perform(click())
        Espresso.onData(CoreMatchers.allOf(CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)), CoreMatchers.`is`("Sve moje ankete"))).perform(click())
        val ankete = AnketaRepository.getMyAnkete()
        onView(withId(R.id.listaAnketa)).perform(
            RecyclerViewActions.actionOnItem<RecyclerView.ViewHolder>(
                CoreMatchers.allOf(hasDescendant(withText(ankete[0].naziv)),
            hasDescendant(withText(ankete[0].nazivIstrazivanja))), click()))
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToLast())
        onView(withId(R.id.pager)).perform(ViewPager2Actions.scrollToFirst())
        onView(withId(R.id.dugmeZaustavi)).perform(click())
        onView(withId(R.id.filterAnketa)).check(matches(isDisplayed()))
        onView(withId(R.id.listaAnketa)).check(matches(isDisplayed()))

    }

}