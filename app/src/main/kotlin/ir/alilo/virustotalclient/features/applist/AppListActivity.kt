package ir.alilo.virustotalclient.features.applist

import android.app.SearchManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.widget.SearchView
import ir.alilo.virustotalclient.R
import kotlinx.android.synthetic.main.activity_main.*

class AppListActivity : AppCompatActivity() {
    lateinit var appListPagerAdapter: AppListPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        appListPagerAdapter = AppListPagerAdapter(this, supportFragmentManager)
        viewPager.adapter = appListPagerAdapter
        tabs.setViewPager(viewPager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_apps, menu)

        val searchManager: SearchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu?.findItem(R.id.apps_search_item)?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(SearchListener())

        return true
    }

    inner class SearchListener : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            appListPagerAdapter.applyFilter(query)
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            appListPagerAdapter.applyFilter(newText)
            return true
        }
    }
}