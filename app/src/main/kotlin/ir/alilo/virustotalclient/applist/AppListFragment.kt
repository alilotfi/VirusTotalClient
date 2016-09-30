package ir.alilo.virustotalclient.applist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.mvp.FragmentView
import kotlinx.android.synthetic.main.fragment_apps.*
import java.util.*

class AppListFragment : FragmentView<AppListPresenter>() {
    val adapter by lazy { AppListAdapter(ArrayList()) }

    override fun getLayoutId() = R.layout.fragment_apps
    override fun newPresenter() = AppListPresenter()

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = adapter
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