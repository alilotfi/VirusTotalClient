package ir.alilo.virustotalclient.injection

import dagger.Component
import ir.alilo.virustotalclient.features.applist.AppListFragment
import ir.alilo.virustotalclient.features.settings.SettingsActivity

@FeatureScope
@Component(modules = arrayOf(AppListModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface AppListComponent {
    fun inject(appListFragment: AppListFragment)
}

@FeatureScope
@Component(modules = arrayOf(SettingsModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface SettingsComponent {
    fun inject(settingsActivity: SettingsActivity)
}
