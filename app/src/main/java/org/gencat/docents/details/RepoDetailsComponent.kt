package org.gencat.docents.details

import dagger.BindsInstance
import dagger.Subcomponent
import dagger.android.AndroidInjector
import org.gencat.docents.di.ScreenScope
import javax.inject.Named

/*
* @BindsInstance Marks a method on a component builder or sub-component builder that allows
* an instance to be bound to some type within the component.
*
* Binding an instance is equivalent to passing an instance to a module constructor and
* providing that instance, but is often more efficient.
*
* When possible, binding object instances should be preferred to using module instances.
* */
@ScreenScope
@Subcomponent
interface RepoDetailsComponent : AndroidInjector<RepoDetailsController> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<RepoDetailsController>() {

        @BindsInstance
        abstract fun bindRepoOwner(@Named("repo_owner") repoOwner: String): Unit

        @BindsInstance
        abstract fun bindRepoName(@Named("repo_name") repoName: String): Unit

        override fun seedInstance(instance: RepoDetailsController?) {
            bindRepoName(instance!!.args.getString(RepoDetailsController.REPO_NAME_KEY))
            bindRepoOwner(instance!!.args.getString(RepoDetailsController.REPO_OWNER_KEY))
        }

    }

}