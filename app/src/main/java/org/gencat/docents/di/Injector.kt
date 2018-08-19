package org.gencat.docents.di

import android.app.Activity

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
    }

}