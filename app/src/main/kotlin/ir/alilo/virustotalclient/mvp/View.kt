package ir.alilo.virustotalclient.mvp

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import javax.inject.Inject

interface MVPView

abstract class FragmentView<P : Presenter<out Any?, out Any?>> : Fragment(), MVPView {
    @Inject lateinit var presenter: P

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(getLayoutId(), container, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDestroy()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
}

abstract class ActivityView<P : Presenter<out Any?, out Any?>> : AppCompatActivity(), MVPView {
    @Inject lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    @LayoutRes
    abstract fun getLayoutId(): Int
}