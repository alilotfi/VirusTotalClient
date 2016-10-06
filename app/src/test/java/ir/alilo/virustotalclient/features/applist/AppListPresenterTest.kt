package ir.alilo.virustotalclient.features.applist

import android.app.Application
import ir.alilo.virustotalclient.injection.AndroidModule
import ir.alilo.virustotalclient.injection.DaggerApplicationComponent
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers
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
        wen(interactor.fetchApps(true, 0))
                .then { presenter.onAppsRetrieved(mutableListOf(), 0) }
        wen(interactor.fetchApps(true, 1))
                .then { presenter.onAppsRetrieved(mutableListOf(), 1) }

        presenter.loadApps(true)

        val order = Mockito.inOrder(view, interactor)
        order.verify(view).showLoading()
        order.verify(interactor).fetchApps(Matchers.eq(true), Matchers.anyInt())
        order.verify(view).showApps(mutableListOf())
        order.verify(view).hideLoading()
    }

    @Test
    fun reloadApps() {

    }
}