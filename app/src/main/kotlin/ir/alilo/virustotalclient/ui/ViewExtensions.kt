package ir.alilo.virustotalclient.ui

import android.view.View

fun View.click(listener: (View) -> Unit) {
    setOnClickListener { listener(this) }
}

var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) = when (value) {
        true -> visibility = View.VISIBLE
        false -> visibility = View.GONE
    }