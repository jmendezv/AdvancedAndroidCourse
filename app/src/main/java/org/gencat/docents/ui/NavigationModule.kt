package org.gencat.docents.ui

import dagger.Binds
import dagger.Module

@Module
abstract class NavigationModule {

    @Binds
    abstract fun provideScreenNavigator(screenNavigator: DefaultScreenNavigator): ScreenNavigator
}