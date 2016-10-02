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
        view?.showLoading()
        interactor?.fetchApps(system, if (system) REQUEST_SYSTEM else REQUEST_NON_SYSTEM)
    }

    fun reloadApps(system: Boolean) {
        view?.showLoading()
        view?.clearApps()
        interactor?.fetchApps(system, if (system) REQUEST_SYSTEM else REQUEST_NON_SYSTEM)
    }

    override fun onAppsRetrieved(apps: List<App>, requestCode: Int) {
        view?.showApps(apps)
        view?.hideLoading()
    }

    interface AppListView {
        fun showLoading()
        fun hideLoading()
        fun showApps(apps: List<App>)
        fun clearApps()
    }
}