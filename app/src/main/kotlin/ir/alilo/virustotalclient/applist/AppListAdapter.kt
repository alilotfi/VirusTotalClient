package ir.alilo.virustotalclient.applist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.datasources.db.App

class AppListAdapter(private val apps: List<App>) :
        RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {
    override fun getItemCount(): Int {
        return apps.size
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(apps[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_app, parent, false)
        return AppViewHolder(view)
    }

    class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appName = itemView.findViewById(R.id.cardApp_name) as TextView
        fun bind(app: App) = with(app) {
            appName.text = name
        }
    }
}