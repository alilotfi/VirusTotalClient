package ir.alilo.virustotalclient.features.applist

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.datasources.db.App
import ir.alilo.virustotalclient.mvp.FragmentView

class AppListFragment : FragmentView<AppListPresenter>(), AppListPresenter.AppListView,
        SwipeRefreshLayout.OnRefreshListener {
    val adapter by lazy { AppListAdapter(mutableListOf()) }
    lateinit var refresh: SwipeRefreshLayout

    override fun getLayoutId() = R.layout.fragment_apps
    override fun newPresenter() = AppListPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val recyclerView = view.findViewById(R.id.apps_list) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter.setHasStableIds(true)
        recyclerView.adapter = adapter

        refresh = view.findViewById(R.id.apps_swipeContainer) as SwipeRefreshLayout
        refresh.setOnRefreshListener(this)

        val system = arguments.getBoolean(EXTRA_SYSTEM_BOOLEAN)
        presenter.loadApps(system)

        return view
    }

    override fun onRefresh() {
        val system = arguments.getBoolean(EXTRA_SYSTEM_BOOLEAN)
        presenter.reloadApps(system)
    }

    fun getTitleId() = if (arguments.getBoolean(EXTRA_SYSTEM_BOOLEAN)) {
        R.string.apps_systemApps
    } else {
        R.string.apps_nonSystemApps
    }

    fun applyFilter(query: String?) {
        query?.let { adapter.filterItems(query) } ?: adapter.filterItems("")
    }

    override fun showLoading() {
        refresh.isRefreshing = true
    }

    override fun hideLoading() {
        refresh.isRefreshing = false
    }

    override fun showApps(apps: List<App>) {
        adapter.addItems(apps)
    }

    override fun clearApps() {
        adapter.clearItems()
    }

    companion object {
        val EXTRA_SYSTEM_BOOLEAN = "system:Boolean"
        fun newInstance(system: Boolean): AppListFragment {
            val fragmentArgument = Bundle().apply {
                putBoolean(EXTRA_SYSTEM_BOOLEAN, system)
            }

            return AppListFragment().apply {
                arguments = fragmentArgument
            }
        }
    }
}