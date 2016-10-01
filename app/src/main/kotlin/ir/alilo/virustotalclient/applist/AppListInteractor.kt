package ir.alilo.virustotalclient.applist

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import ir.alilo.virustotalclient.VirusTotal
import ir.alilo.virustotalclient.datasources.db.App
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject

class AppListInteractor(val listener: AppListListener) {
    @Inject lateinit var pm: PackageManager

    init {
        VirusTotal.getGraph().inject(this)
    }

    fun fetchApps(system: Boolean, requestCode: Int) {
        var flag = PackageManager.GET_META_DATA
        if (system) {
            flag = flag or PackageManager.MATCH_SYSTEM_ONLY
        }

        doAsync(exceptionHandler = Throwable::printStackTrace) {
            val apps = pm.getInstalledApplications(flag)
                    .filter { isSystemApp(it) == system }
                    .map { toApp(it) }
                    .sortedBy { it.name?.toUpperCase() }
            // TODO: Replace with case insensitive string comparison

            uiThread { listener.onAppsRetrieved(apps, requestCode) }
        }
    }

    private fun isSystemApp(appInfo: ApplicationInfo): Boolean = with(appInfo) {
        flags and ApplicationInfo.FLAG_SYSTEM != 0
    }

    private fun toApp(appInfo: ApplicationInfo) = with(appInfo) {
        App(packageName, pm.getApplicationLabel(appInfo).toString(), pm.getApplicationIcon(appInfo),
                isSystemApp(appInfo))
    }

    interface AppListListener {
        fun onAppsRetrieved(apps: List<App>, requestCode: Int)
    }
}