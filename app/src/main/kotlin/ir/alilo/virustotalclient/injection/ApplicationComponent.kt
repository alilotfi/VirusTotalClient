package ir.alilo.virustotalclient.injection

import dagger.Component
import ir.alilo.virustotalclient.VirusTotal
import ir.alilo.virustotalclient.applist.AppListInteractor
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun inject(application: VirusTotal)
    fun inject(appListInteractor: AppListInteractor)
}