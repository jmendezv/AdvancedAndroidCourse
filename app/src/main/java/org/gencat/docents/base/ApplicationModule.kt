package org.gencat.docents.base

import android.app.Application
import dagger.Module
import dagger.Provides

/*
* This class is a provider of dependencies
* */
@Module
class ApplicationModule constructor(
        private val application: Application) {

    @Provides
    fun provideApplicationContext() = application

}