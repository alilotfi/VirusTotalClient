package ir.alilo.virustotalclient.mvp

abstract class Presenter<V, I> {
    var view: V? = null
    var interactor: I? = null

    @Suppress("LeakingThis")
    constructor(view: V) {
        this.view = view
        interactor = newInteractor()
    }

    open fun onCreate() {
    }

    open fun onDestroy() {
        view = null
        interactor = null
    }

    abstract fun newInteractor(): I
}