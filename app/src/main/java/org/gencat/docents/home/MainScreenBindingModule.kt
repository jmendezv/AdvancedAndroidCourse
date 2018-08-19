package org.gencat.docents.home

import com.bluelinelabs.conductor.Controller
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import org.gencat.docents.trending.TrendingRepoComponent
import org.gencat.docents.trending.TrendingRepoController

@Module(subcomponents = [TrendingRepoComponent::class])
abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingRepoController::class)
    abstract fun bindTrendingReposInjector(
            builder: TrendingRepoComponent.Builder):
            AndroidInjector.Factory<out Controller>

}