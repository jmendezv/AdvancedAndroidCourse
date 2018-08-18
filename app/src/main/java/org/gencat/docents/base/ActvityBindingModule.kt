package org.gencat.docents.base

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import org.gencat.docents.home.MainActivity
import org.gencat.docents.home.MainActivityComponent

@Module(subcomponents = [MainActivityComponent::class])
abstract class ActvityBindingModule {

    /* @Binds provides a replacement of @Provides methods which simply return the function's argument */

    /*
    * @IntoMap The method's return type forms the type argument for the value of a Map<K, Provider<V>>,
    * and the combination of the annotated key and the returned value is contributed to the map as a
    * key/value pair.
    *
    * The Map<K, Provider<V>> produced from the accumulation of values will be immutable.
    * */

    /* @ActivityKey key to the map  */

    /* This method returns a map of class literals to activities injectors */
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    abstract fun provideMainActivityInjector(
            builder: MainActivityComponent.Builder):
            AndroidInjector.Factory<out Activity>
}