package org.gencat.docents.trending

import dagger.Subcomponent
import dagger.android.AndroidInjector
import org.gencat.docents.di.ScreenScope

@ScreenScope
@Subcomponent
interface TrendingRepoComponent : AndroidInjector<TrendingRepoController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<TrendingRepoController>() {

    }
}