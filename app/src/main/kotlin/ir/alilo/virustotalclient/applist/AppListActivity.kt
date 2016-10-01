package ir.alilo.virustotalclient.applist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ir.alilo.virustotalclient.R
import kotlinx.android.synthetic.main.activity_main.*

class AppListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        val appListsAdapter = AppListPagerAdapter(this, supportFragmentManager)
        container.adapter = appListsAdapter
        tabs.setupWithViewPager(container)
    }
}