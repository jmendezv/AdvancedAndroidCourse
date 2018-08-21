package org.gencat.docents.trending

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.gencat.docents.R
import org.gencat.docents.di.ScreenScope
import org.gencat.docents.model.Repo
import timber.log.Timber
import javax.inject.Inject


/*
* A Relay is an observable and a consumer, very similar to a Subject
* in RxJava, but it does not pass on any terminal events. So when
* onError() or onComplete() methods are called, the stream is dead.
*
* We never want it to happen to our ViewModel stakeholders.
*
* So essentially, onError() and onComplete() methods are ignored and we
* are expected to handle them ourselves if needed.
*
* */
@ScreenScope
class TrendingReposViewModel @Inject constructor() {

    /* BehaviourRelay extends io.reactivex.Observable<T> */
    /* We need three relays to hold the state of this ViewModel */
    private val reposRelay = BehaviorRelay.create<List<Repo>>()
    /* String resource as integer */
    private val errorRelay = BehaviorRelay.create<Integer>()
    /* If it is loading or not */
    private val loadingRelay = BehaviorRelay.create<Boolean>()

    /*
    * We need to expose these as Observables to the view.
    * These are what our view will subscribe to
    * */
    fun getLoading(): Observable<Boolean> = loadingRelay

    fun getRepos(): Observable<List<Repo>> = reposRelay

    fun getError(): Observable<Integer> = errorRelay

    /*
    * To update these fields, remember they are observables and consumers
    * */

    fun setLoading(): Consumer<Boolean> = loadingRelay

    fun setRepos(): Consumer<List<Repo>> {
        errorRelay.accept(Integer(-1))
        return reposRelay
    }

    fun onError(): Consumer<Throwable> {
        return Consumer {
            Timber.e(it, "Error loading repos")
            errorRelay.accept(Integer(R.string.api_error_repos))
        }
    }


}