package ir.alilo.virustotalclient.features.applist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.datasources.db.App
import ir.alilo.virustotalclient.ui.click

class AppListAdapter(val presenter: AppListPresenter, private val items: MutableList<App>) :
        RecyclerView.Adapter<AppListAdapter.AppViewHolder>() {
    val visibleItems: MutableList<App> = mutableListOf()

    init {
        visibleItems.addAll(items)
    }

    fun addItems(apps: List<App>) {
        items.addAll(apps)

        val lastSize = visibleItems.size
        visibleItems.addAll(apps)
        notifyItemRangeInserted(lastSize, apps.size)
    }

    fun clearItems() {
        items.clear()

        val lastSize = visibleItems.size
        visibleItems.clear()
        notifyItemRangeRemoved(0, lastSize)
    }

    fun filterItems(query: String) {
        val newItems = items.filter { it.name?.contains(query, true) ?: true }

        visibleItems.clear()
        visibleItems.addAll(newItems)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return visibleItems.size
    }

    override fun getItemId(position: Int): Long {
        return visibleItems[position].hashCode().toLong()
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.bind(visibleItems[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.card_app, parent, false)
        return AppViewHolder(view)
    }

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val appName = itemView.findViewById(R.id.cardApp_name) as TextView
        val appIcon = itemView.findViewById(R.id.cardApp_icon) as ImageView
        fun bind(app: App) = with(app) {
            appName.text = name
            appIcon.setImageDrawable(icon)

            itemView.click { presenter.rescanApp(items[adapterPosition]) }
        }
    }
}