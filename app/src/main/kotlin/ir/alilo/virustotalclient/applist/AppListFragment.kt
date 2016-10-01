package ir.alilo.virustotalclient.applist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.datasources.db.App
import ir.alilo.virustotalclient.mvp.FragmentView
import java.util.*

class AppListFragment : FragmentView<AppListPresenter>(), AppListPresenter.AppListView {
    val adapter by lazy { AppListAdapter(ArrayList()) }

    override fun getLayoutId() = R.layout.fragment_apps
    override fun newPresenter() = AppListPresenter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        val recyclerView = view.findViewById(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter

        val system = arguments.getBoolean(EXTRA_SYSTEM_BOOLEAN)
        presenter.loadApps(system)

        return view
    }

    fun getTitleId() = if (arguments.getBoolean(EXTRA_SYSTEM_BOOLEAN)) {
        R.string.apps_systemApps
    } else {
        R.string.apps_nonSystemApps
    }

    override fun showApps(apps: List<App>) {
        adapter.addItems(apps)
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