package ir.alilo.virustotalclient

import android.app.Application
import ir.alilo.virustotalclient.datasources.Preference
import ir.alilo.virustotalclient.injection.AndroidModule
import ir.alilo.virustotalclient.injection.ApplicationComponent
import ir.alilo.virustotalclient.injection.DaggerApplicationComponent

class VirusTotalClient : Application() {
    override fun onCreate() {
        super.onCreate()
        objectGraph = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
        objectGraph.inject(this)

        Preference.context = this
    }

    companion object {
        private lateinit var objectGraph: ApplicationComponent
        fun getGraph() = objectGraph
    }
}