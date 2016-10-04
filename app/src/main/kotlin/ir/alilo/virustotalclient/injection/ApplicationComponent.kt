package ir.alilo.virustotalclient.injection

import android.content.pm.PackageManager
import dagger.Component
import ir.alilo.virustotalclient.VirusTotal
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun inject(application: VirusTotal)
    fun getPackageManager(): PackageManager
}