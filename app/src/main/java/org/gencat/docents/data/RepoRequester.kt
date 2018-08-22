package org.gencat.docents.data

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.gencat.docents.model.Contributor
import org.gencat.docents.model.Repo
import org.gencat.docents.model.TrendingReposResponse
import javax.inject.Inject

/*
* RxJava is a reactive stream library that focuses in the
* Pub/Sub pattern. Where you have publishers that publish
* items and you have subscribers that subscribe to those
* publishers and consume those items.
*
* In short, you have observables which are the publishers that
* emit items. Subscribers subscribe directly to a publishers.
*
* RxJava provides tones of operators to mutate streams into
* another types.
*
* When you subscribe a disposable is returned that represents
* the connections between the consumer and observable.
*
* You can break the connections between both calling the dispose()
* method on the disposable object to prevent possible memory leaks.
*
* */
class RepoRequester @Inject constructor(val service: RepoService) {

    fun getTrendingRepos(): Single<List<Repo>> =
            service.getTrendingRepos()
                    .map(TrendingReposResponse::repos)
                    //.subscribeOn(Schedulers.io())

    fun getTrendingRepo(repoName: String, repoOwner: String): Single<Repo> =
            service.getTrendingRepo(repoName, repoOwner)
                    //.subscribeOn(Schedulers.io())

    fun getContributors(url: String): Single<List<Contributor>> =
            service.getContributors(url)
                    //.subscribeOn(Schedulers.io())
}