package ir.alilo.virustotalclient.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class AppListsAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        throw UnsupportedOperationException("Not implemented")
    }

    override fun getCount(): Int {
        return 2
    }
}