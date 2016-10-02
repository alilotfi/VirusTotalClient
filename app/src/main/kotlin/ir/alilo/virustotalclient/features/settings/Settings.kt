package ir.alilo.virustotalclient.features.settings

import android.content.Context
import android.support.annotation.StringRes
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.features.settings.about.AboutActivity
import org.jetbrains.anko.intentFor

interface SettingItem {
    operator fun invoke(context: Context)
    @StringRes
    fun getTitleId(): Int
}

object SettingAbout : SettingItem {
    override fun invoke(context: Context) = with(context) {
        startActivity(intentFor<AboutActivity>())
    }

    @StringRes
    override fun getTitleId() = R.string.settings_about
}

val settings = listOf(SettingAbout)