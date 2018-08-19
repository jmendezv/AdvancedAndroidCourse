package org.gencat.docents.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import org.gencat.docents.di.Injector

/*
* Controller (Light weight Activity/Fragment) instances are retain across config changes
* */
abstract class BaseController : Controller() {

    var injected = false

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

}