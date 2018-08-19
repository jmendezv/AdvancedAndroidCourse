package org.gencat.docents.base

import android.app.Application
import org.gencat.docents.di.ActivityInjector
import javax.inject.Inject

class MyApplication: Application() {

    private lateinit var component: ApplicationComponent

    @Inject
    lateinit var activityInjector: ActivityInjector

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                // Dagger marks it a deprecated because it is not used yet
                .applicationModule(ApplicationModule(this))
                .build()
        component.inject(this)
    }

}