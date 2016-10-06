package ir.alilo.virustotalclient.features.settings


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import ir.alilo.virustotalclient.R
import ir.alilo.virustotalclient.VirusTotal
import ir.alilo.virustotalclient.injection.DaggerSettingsComponent
import ir.alilo.virustotalclient.injection.SettingsModule
import ir.alilo.virustotalclient.mvp.ActivityView
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : ActivityView<SettingsPresenter>(), SettingsPresenter.SettingsView {

    override fun getLayoutId() = R.layout.activity_settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerSettingsComponent.builder()
                .applicationComponent(VirusTotal.getGraph())
                .settingsModule(SettingsModule(this))
                .build()
                .inject(this)

        setSupportActionBar(toolbar)
        settingsList.setHasFixedSize(true)
        settingsList.layoutManager = LinearLayoutManager(this)
        settingsList.adapter = SettingsAdapter(settings)
    }
}
