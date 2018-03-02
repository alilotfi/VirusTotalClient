package ir.alilo.virustotalclient.injection

import dagger.Module
import dagger.Provides
import ir.alilo.virustotalclient.features.applist.AppListInteractor
import ir.alilo.virustotalclient.features.applist.AppListInteractorImpl
import ir.alilo.virustotalclient.features.applist.AppListPresenter
import ir.alilo.virustotalclient.features.applist.AppListView
import ir.alilo.virustotalclient.features.settings.SettingsPresenter

@Module
abstract class FeatureModule<out V : Any>(val view: V) {
    @Provides
    @FeatureScope
    fun provideView() = view
}

@Module
class AppListModule(view: AppListView) : FeatureModule<AppListView>(view) {
    @Provides
    @FeatureScope
    fun provideInteractor(appListInteractorImpl: AppListInteractorImpl): AppListInteractor = appListInteractorImpl
}

@Module
class SettingsModule(view: SettingsPresenter.SettingsView) : FeatureModule<SettingsPresenter.SettingsView>(view)
