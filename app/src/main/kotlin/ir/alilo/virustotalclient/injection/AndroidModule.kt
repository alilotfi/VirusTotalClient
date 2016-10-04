package ir.alilo.virustotalclient.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ir.alilo.virustotalclient.features.applist.AppListInteractor
import ir.alilo.virustotalclient.features.applist.AppListPresenter
import javax.inject.Singleton

@Module
open class AndroidModule(val application: Application) {
    @Provides
    @Singleton
    open fun provideApplication() = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    open fun providePackageManager() = application.packageManager!!

    @Provides
    open fun provideAppListListener(appListPresenter: AppListPresenter): AppListInteractor.AppListListener {
        return appListPresenter
    }
}