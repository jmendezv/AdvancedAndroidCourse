package org.gencat.docents.ui

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import org.gencat.docents.lifecycle.ActivityLifeCycleTask

@Module
abstract class NavigationModule {

    @Binds
    abstract fun provideScreenNavigator(screenNavigator: DefaultScreenNavigator):
            ScreenNavigator

    @Binds
    @IntoSet
    abstract fun bindScreenNavigatorTask(screenNavigator: DefaultScreenNavigator):
            ActivityLifeCycleTask
}