package ir.alilo.virustotalclient.features.applist

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class AppListPagerAdapter(val context: Context, fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager) {
    val nonSystemFragment = AppListFragment.newInstance(false)
    val systemFragment = AppListFragment.newInstance(true)
    val fragments = listOf(nonSystemFragment, systemFragment)

    override fun getItem(position: Int): Fragment = fragments[position]

    override fun getPageTitle(position: Int) = context.getString(fragments[position].getTitleId())!!

    override fun getCount(): Int {
        return fragments.size
    }

    fun applyFilter(query: String?) = fragments.forEach { it.applyFilter(query) }
}