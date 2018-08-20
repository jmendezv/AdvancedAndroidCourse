package org.gencat.docents.base

import dagger.Component
import org.gencat.docents.data.RepoServiceModule
import org.gencat.docents.home.MainScreenBindingModule
import org.gencat.docents.networking.ServiceModule

/*
* A Dagger Component is the object that actually injects classes.
*
* It is build with all the dependencies of its own scope and
* the dependencies that are built on top of.
*
* We define the interface and Dagger will generate the implementation.
*
* The lifecycle is essentially the lifecycle of the app.
* */
@Component(modules = [
    ApplicationModule::class,
    ActivityBindingModule::class,
    ServiceModule::class,
    RepoServiceModule::class])
interface ApplicationComponent {

    fun inject(myApplication: MyApplication)

}