package ba.etf.rma22.projekat.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ba.etf.rma22.projekat.viewPager


class ViewPagerAdapter(var items : MutableList<Fragment>, fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun refreshFragment(index: Int, fragment: Fragment) {
        items[index] = fragment
        notifyItemChanged(index)
    }

    fun refreshAll(fragments : List<Fragment>, pozicija : Int) {
        items.removeAll(items)
        items.addAll(fragments)
        notifyDataSetChanged()
        viewPager.currentItem = pozicija
    }

    fun remove(index: Int) {
        items.removeAt(index)
        notifyItemChanged(index)
    }

    fun add(index: Int, fragment: Fragment) {
        items.add(index, fragment)
        notifyItemChanged(index)
    }

    override fun getItemId(position: Int): Long {
        return items[position].hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return items.find { it.hashCode().toLong() == itemId } != null
    }
}