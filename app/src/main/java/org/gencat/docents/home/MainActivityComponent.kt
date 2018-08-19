package org.gencat.docents.home

import dagger.Subcomponent
import dagger.android.AndroidInjector
import org.gencat.docents.di.ActivityScope
import org.gencat.docents.di.AndroidInjectorBuilder

@ActivityScope
// It will be built on top of our Application Component
@Subcomponent(modules = [MainScreenBindingModule::class])
/*
* Performs members-injection for a concrete subtype of a core Android type
*
* */
interface MainActivityComponent : AndroidInjector<MainActivity> {

    /*
    * A builder for a subcomponent
    * */
    @Subcomponent.Builder
    abstract class Builder : AndroidInjectorBuilder()

}