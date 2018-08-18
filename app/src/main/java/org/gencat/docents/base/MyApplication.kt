package org.gencat.docents.base

import android.app.Application

class MyApplication: Application() {

    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                // Dagger marks it a deprecated because it is not used yet
                .applicationModule(ApplicationModule(this))
                .build()
    }

}