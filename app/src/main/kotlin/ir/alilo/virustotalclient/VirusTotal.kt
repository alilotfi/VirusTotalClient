package ir.alilo.virustotalclient

import android.app.Application
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowManager
import ir.alilo.virustotalclient.datasources.Preference
import ir.alilo.virustotalclient.injection.AndroidModule
import ir.alilo.virustotalclient.injection.ApplicationComponent
import ir.alilo.virustotalclient.injection.DaggerApplicationComponent

class VirusTotal : Application() {
    override fun onCreate() {
        super.onCreate()
        objectGraph = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()

        FlowManager.init(FlowConfig.Builder(this).build())

        Preference.context = this
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }

    companion object {
        private lateinit var objectGraph: ApplicationComponent
        fun getGraph() = objectGraph
    }
}