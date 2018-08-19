package org.gencat.docents.di

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.android.AndroidInjector
import org.gencat.docents.base.BaseActivity
import org.gencat.docents.base.MyApplication
import javax.inject.Inject
import javax.inject.Provider

/*
 * A kind of DispatchingAndroidInjector but with activities retention state between configuration changes
 *
 * The Dagger Android library that we imported has a really nice class call
 * AndroidInjection. It allows us to inject Android components in a base class
 * in a way that subclasses don't need to know anything about the dependency
 * injection mechanism. But the downside of the implementation of this class
 * in our case is that each time you inject an Android component (Activity,
 * Fragment) it creates a new instance of the component.
 *
 * Which means that all dependencies are reset. However there a many benefits
 * to retaining activities scope beyond configuration changes.
 *
 * Dependencies scope should not care about components lifecycle but only
 * about components being on scope or out of scope.
 *
 * So, basically we are rewriting the AndroidInjection class with a reuse
 * feature in mind by means of a cache.
 *
 * The ActivityInjector is kept in Application scope
 * */
class ActivityInjector @Inject constructor(
        private val activityInjectorMap:
        Map<Class<out Activity>,
                @JvmSuppressWildcards Provider<AndroidInjector.Factory<out Activity>>>) {

    /* out T is like ? extends T. Provides read-only operations. T is a Covariant type. */
    private val cache: MutableMap<String?, AndroidInjector<out Activity>> = mutableMapOf()

    fun inject(activity: Activity): Unit {

        if (activity !is BaseActivity)
            throw IllegalArgumentException("Activity must extend BaseActivity")

        /* Automatic cast to BaseActivity */
        val instanceId = activity.instanceId
        /* If activity's android injector present in cache then inject it */
        /* Injects the members of instance */
        if (cache.containsKey(instanceId)) {
            (cache[instanceId] as AndroidInjector<Activity>).inject(activity)
        }
        /* If activity's android injector not present in cache then add to cache and inject it */
        else {
            val androidInjectorFactory: AndroidInjector.Factory<Activity> =
                    activityInjectorMap[activity.javaClass]?.get() as AndroidInjector.Factory<Activity>
            val androidInjector: AndroidInjector<Activity> =
                    androidInjectorFactory.create(activity)
            cache[instanceId] = androidInjector
            androidInjector.inject(activity)
        }
    }

    fun clear(activity: Activity): Unit {
        if (activity !is BaseActivity)
            throw IllegalArgumentException("Activity must extend BaseActivity")
        /* Implicit cast to BaseActivity  */
        val instanceId = activity.instanceId
        if (cache.containsKey(instanceId))
            cache.remove(instanceId)
    }

    @Module
    companion object Companion {
        @Provides
        fun get(context: Context): ActivityInjector =
                (context.applicationContext as MyApplication).activityInjector
    }

}