package ir.alilo.virustotalclient.mvp

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup

interface View<out P : Presenter> {
    fun newPresenter(): P
}

abstract class FragmentView<out P : Presenter> : Fragment(), View<P> {
    val presenter: P by lazy { newPresenter() }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): android.view.View? {
        presenter.onCreate()
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
}