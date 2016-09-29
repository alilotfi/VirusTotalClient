package ir.alilo.virustotalclient.ui

import android.view.View

fun View.click(listener: (View) -> Unit) {
    setOnClickListener { listener(this) }
}