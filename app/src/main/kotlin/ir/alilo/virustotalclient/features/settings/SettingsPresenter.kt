package ir.alilo.virustotalclient.features.settings

import ir.alilo.virustotalclient.mvp.Presenter

class SettingsPresenter(view: SettingsView) :
        Presenter<SettingsPresenter.SettingsView, SettingsInteractor>(view) {
    override fun newInteractor() = SettingsInteractor()

    interface SettingsView
}