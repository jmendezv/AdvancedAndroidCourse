package org.gencat.docents.base

import android.app.Application
import dagger.Module
import dagger.Provides

/*
* This class is a provider of dependencies
* */
@Module
class ApplicationModule public constructor(private val application: Application) {

    @Provides
    fun provideApplicationContext() = application

}