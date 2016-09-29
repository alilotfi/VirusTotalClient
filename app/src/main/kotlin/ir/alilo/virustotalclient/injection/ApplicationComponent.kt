package ir.alilo.virustotalclient.injection

import dagger.Component
import ir.alilo.virustotalclient.VirusTotalClient
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun inject(application: VirusTotalClient)
}