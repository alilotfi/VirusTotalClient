package ir.alilo.virustotalclient.features.applist

import android.app.Application
import ir.alilo.virustotalclient.datasources.db.App
import ir.alilo.virustotalclient.injection.AndroidModule
import ir.alilo.virustotalclient.injection.DaggerApplicationComponent
import ir.alilo.virustotalclient.utils.callbackWithCode
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
import org.mockito.Matchers.eq
import org.mockito.Mockito
import org.mockito.Mockito.`when` as wen

class AppListPresenterTest {
    lateinit var view: AppListPresenter.AppListView
    lateinit var presenter: AppListPresenter
    lateinit var interactor: AppListInteractor

    init {
        val mockApplication = Mockito.mock(Application::class.java)!!
        DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(mockApplication))
                .build()
    }

    @Before
    fun setUp() {
        view = Mockito.mock(AppListPresenter.AppListView::class.java)
        interactor = Mockito.mock(AppListInteractor::class.java)
        presenter = AppListPresenter(view, interactor)
    }

    @Test
    fun loadSystemApps() {
        val appsList: List<App> = mutableListOf()

        wen(interactor.fetchApps(eq(true), Matchers.anyInt()))
                .callbackWithCode { presenter.onAppsRetrieved(appsList, it) }

        presenter.loadApps(true)

        checkLoadAppsResult(appsList, true)
    }

    @Test
    fun loadRegularApps() {
        val appsList: List<App> = mutableListOf()

        wen(interactor.fetchApps(eq(false), Matchers.anyInt()))
                .callbackWithCode { presenter.onAppsRetrieved(appsList, it) }

        presenter.loadApps(false)

        checkLoadAppsResult(appsList, false)
    }

    private fun checkLoadAppsResult(appsList: List<App>, system: Boolean) {
        val order = Mockito.inOrder(view, interactor)
        order.verify(view).showLoading()
        order.verify(interactor).fetchApps(eq(system), Matchers.anyInt())
        order.verify(view).showApps(appsList)
        order.verify(view).hideLoading()
    }

    @Test
    fun reloadSystemApps() {
        val appsList: List<App> = mutableListOf()

        wen(interactor.fetchApps(eq(true), Matchers.anyInt()))
                .callbackWithCode { presenter.onAppsRetrieved(appsList, it) }

        presenter.reloadApps(true)

        checkReloadAppsResult(appsList, true)
    }

    @Test
    fun reloadRegularApps() {
        val appsList: List<App> = mutableListOf()

        wen(interactor.fetchApps(eq(false), Matchers.anyInt()))
                .callbackWithCode { presenter.onAppsRetrieved(appsList, it) }

        presenter.reloadApps(false)

        checkReloadAppsResult(appsList, false)
    }

    private fun checkReloadAppsResult(appsList: List<App>, system: Boolean) {
        val order = Mockito.inOrder(view, interactor)
        order.verify(view).showLoading()
        order.verify(view).clearApps()
        order.verify(interactor).fetchApps(eq(system), Matchers.anyInt())
        order.verify(view).showApps(appsList)
        order.verify(view).hideLoading()
    }
}