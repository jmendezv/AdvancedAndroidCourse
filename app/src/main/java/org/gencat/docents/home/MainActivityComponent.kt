package org.gencat.docents.home

import dagger.Subcomponent
import dagger.android.AndroidInjector
import org.gencat.docents.di.ActivityScope

@ActivityScope
// It will be built on top of our Application Component
@Subcomponent
/*
* Performs members-injection for a concrete subtype of a core Android type
*
* */
interface MainActivityComponent : AndroidInjector<MainActivity> {

    /*
    * A builder for a subcomponent
    * */
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}