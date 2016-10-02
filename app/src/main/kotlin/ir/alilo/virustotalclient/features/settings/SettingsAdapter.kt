package ir.alilo.virustotalclient.features.settings

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.ui.click

class SettingsAdapter(val settings: List<SettingItem>) :
        RecyclerView.Adapter<SettingsAdapter.BasicViewHolder>() {
    override fun getItemCount() = settings.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_setting, parent, false)
        return BasicViewHolder(view)
    }

    override fun onBindViewHolder(holder: BasicViewHolder?, position: Int) {
        holder?.bind(settings[position])
    }

    inner class BasicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView by lazy { itemView.findViewById(R.id.item_setting_title) as TextView }
        fun bind(item: SettingItem) {
            val ctx = itemView.context
            title.text = ctx.getString(item.getTitleId())
            itemView.click { settings[adapterPosition](ctx) }
        }
    }
}
