package ir.alilo.virustotalclient.injection

import android.content.pm.PackageManager
import dagger.Component
import ir.alilo.virustotalclient.datasources.api.API
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {
    fun getPackageManager(): PackageManager
    fun getAPI(): API
}