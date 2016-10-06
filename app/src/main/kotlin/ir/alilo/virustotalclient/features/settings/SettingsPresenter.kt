package ir.alilo.virustotalclient.features.settings

import ir.alilo.virustotalclient.mvp.Presenter
import javax.inject.Inject

class SettingsPresenter @Inject constructor(view: SettingsView, interactor: SettingsInteractor) :
        Presenter<SettingsPresenter.SettingsView, SettingsInteractor>(view, interactor) {

    interface SettingsView
}