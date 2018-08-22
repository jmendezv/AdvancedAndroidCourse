package org.gencat.docents.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.bluelinelabs.conductor.Controller
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.gencat.docents.di.Injector

/*
* Controller (Light weight Activity/Fragment) instances are retain across config changes
* */
abstract class BaseController(bundle: Bundle? = null) : Controller(bundle) {

    /* Allows to add multiple disposables and to dispose() all of them at once */
    private val disposables: CompositeDisposable = CompositeDisposable()
    private var injected = false
    private var unbinder: Unbinder? = null

    /*
    * Called when this Controller has a Context available to it.
    * This will happen very early on in the lifecycle (before a view is created).
    * If the host activity is re-created (ex: for orientation change),
    * this will be called again when the new context is available.
    * */
    override fun onContextAvailable(context: Context) {
        // just to ensure we don't waste time reinjecting
        if (!injected) {
            Injector.inject(this)
            injected = true
        }
        super.onContextAvailable(context)
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(layoutRes(), container, false)
        unbinder = ButterKnife.bind(this, view)
        onViewBound(view)
        disposables.addAll(*subscriptions())
        return view
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        disposables.clear()
        unbinder?.run {
            unbind()
            unbinder = null
        }
    }

    protected open fun onViewBound(view: View): Unit {

    }

    protected open fun subscriptions(): Array<Disposable> = arrayOf()

    @LayoutRes
    protected abstract fun layoutRes(): Int

}