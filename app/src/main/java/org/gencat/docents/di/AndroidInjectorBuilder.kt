package org.gencat.docents.di

import dagger.android.AndroidInjector
import org.gencat.docents.home.MainActivity

/*
* Overriding this method prevents injecting MainActivity
* */
abstract class AndroidInjectorBuilder : AndroidInjector.Builder<MainActivity>() {
    override fun seedInstance(instance: MainActivity?) {
    }
}