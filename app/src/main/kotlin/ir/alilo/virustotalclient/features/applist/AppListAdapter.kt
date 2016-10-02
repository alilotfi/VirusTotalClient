package ir.alilo.virustotalclient.features.applist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.datasources.db.App
import java.util.*

class AppListAdapter(private val items: ArrayList<App>) :
        RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {

    fun addItems(apps: List<App>) {
        val lastSize = items.size
        items.addAll(apps)
        notifyItemRangeInserted(lastSize, apps.size)
    }

    fun clearItems() {
        val lastSize = items.size
        items.clear()
        notifyItemRangeRemoved(0, lastSize)
    }

    fun getItems() = items

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_app, parent, false)
        return AppViewHolder(view)
    }

    class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appName = itemView.findViewById(R.id.cardApp_name) as TextView
        val appIcon = itemView.findViewById(R.id.cardApp_icon) as ImageView
        fun bind(app: App) = with(app) {
            appName.text = name
            appIcon.setImageDrawable(icon)
        }
    }
}