package ir.alilo.virustotalclient.injection

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import ir.alilo.virustotalclient.datasources.api.API
import javax.inject.Singleton

@Module
class AndroidModule(val application: Application) {
    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providePackageManager() = application.packageManager!!

    @Provides
    @Singleton
    fun provideAPI() = API.create()
}