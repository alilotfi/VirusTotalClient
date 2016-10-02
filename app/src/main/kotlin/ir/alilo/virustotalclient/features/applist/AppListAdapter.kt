package ir.alilo.virustotalclient.features.applist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.datasources.db.App

class AppListAdapter(private val totalItems: MutableList<App>) :
        RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {
    val showingItems: MutableList<App> = mutableListOf()

    init {
        showingItems.addAll(totalItems)
    }

    fun addItems(apps: List<App>) {
        totalItems.addAll(apps)

        val lastSize = showingItems.size
        showingItems.addAll(apps)
        notifyItemRangeInserted(lastSize, apps.size)
    }

    fun clearItems() {
        totalItems.clear()

        val lastSize = showingItems.size
        showingItems.clear()
        notifyItemRangeRemoved(0, lastSize)
    }

    fun filterItems(query: String) {
        val newItems = totalItems.filter { it.name?.contains(query, true) ?: true }

        showingItems.clear()
        showingItems.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return showingItems.size
    }

    override fun getItemId(position: Int): Long {
        return showingItems[position].hashCode().toLong()
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(showingItems[position])
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