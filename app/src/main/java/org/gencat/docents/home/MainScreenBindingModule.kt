package org.gencat.docents.home

import com.bluelinelabs.conductor.Controller
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import org.gencat.docents.details.RepoDetailsComponent
import org.gencat.docents.details.RepoDetailsController
import org.gencat.docents.trending.TrendingRepoComponent
import org.gencat.docents.trending.TrendingRepoController

@Module(subcomponents = [TrendingRepoComponent::class, RepoDetailsComponent::class])
abstract class MainScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingRepoController::class)
    abstract fun bindTrendingReposInjector(
            builder: TrendingRepoComponent.Builder):
            AndroidInjector.Factory<out Controller>

    @Binds
    @IntoMap
    @ControllerKey(RepoDetailsController::class)
    abstract fun bindRepoDetailsInjector(
            builder: RepoDetailsComponent.Builder):
            AndroidInjector.Factory<out Controller>

}