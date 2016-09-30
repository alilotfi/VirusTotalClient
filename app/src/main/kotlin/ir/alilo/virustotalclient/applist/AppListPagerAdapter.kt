package ir.alilo.virustotalclient.applist

import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class AppListPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    companion object {
        val FRAGMENT_NON_SYSTEM = 0
        val FRAGMENT_SYSTEM = 1
        val fragments = listOf(FRAGMENT_NON_SYSTEM, FRAGMENT_SYSTEM)
    }

    val systemFragment = AppListFragment.newInstance(true)
    val nonSystemFragment = AppListFragment.newInstance(false)

    override fun getItem(position: Int) = when (position) {
        FRAGMENT_NON_SYSTEM -> systemFragment
        FRAGMENT_SYSTEM -> nonSystemFragment
        else -> throw IllegalArgumentException("Illegal fragment index")
    }

    override fun getCount(): Int {
        return fragments.size
    }
}