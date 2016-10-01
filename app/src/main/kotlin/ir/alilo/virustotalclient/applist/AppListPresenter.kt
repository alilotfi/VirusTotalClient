package ir.alilo.virustotalclient.applist

import ir.alilo.virustotalclient.datasources.db.App
import ir.alilo.virustotalclient.mvp.Presenter

class AppListPresenter(view: AppListView) :
        Presenter<AppListPresenter.AppListView, AppListInteractor>(view),
        AppListInteractor.AppListListener {
    companion object {
        val REQUEST_SYSTEM = 0
        val REQUEST_NON_SYSTEM = 0
    }

    override fun newInteractor() = AppListInteractor(this)

    fun loadApps(system: Boolean) {
        interactor?.fetchApps(system, if (system) REQUEST_SYSTEM else REQUEST_NON_SYSTEM)
    }

    override fun onAppsRetrieved(apps: List<App>, requestCode: Int) {
        view?.showApps(apps)
    }

    interface AppListView {
        fun showApps(apps: List<App>)
    }
}