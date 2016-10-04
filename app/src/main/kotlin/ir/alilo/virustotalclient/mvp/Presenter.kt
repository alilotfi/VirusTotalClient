package ir.alilo.virustotalclient.mvp

abstract class Presenter<V, I>(var view: V?, var interactor: I?) {
    fun onDestroy() {
        view = null
        interactor = null
    }
}