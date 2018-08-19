package org.gencat.docents.di

import android.app.Activity
import com.bluelinelabs.conductor.Controller

/*
* Bridge class between ActivityInjector and MainActivity
*
* */
class Injector private constructor() {

    companion object {
        fun inject(activity: Activity): Unit {
            ActivityInjector.get(activity).inject(activity)
        }

        fun clearComponent(activity: Activity) {
            ActivityInjector.get(activity).clear(activity)
        }

        /*
        * Conductor Controllers are Views/Fragments
        * */
        fun inject(controller: Controller) {
            ScreenInjector.get(controller.activity!!).inject(controller)
        }

        fun clearComponent(controller: Controller) {
            ScreenInjector.get(controller.activity!!).clearController(controller)
        }
    }

}