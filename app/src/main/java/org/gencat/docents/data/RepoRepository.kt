package org.gencat.docents.data

import io.reactivex.Maybe
import io.reactivex.Single
import org.gencat.docents.model.Repo
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class RepoRepository @Inject constructor(val repoRequesterProvider: Provider<RepoRequester>) {

    private val cachedTrendingRepos = mutableListOf<Repo>()

    /*
    * If cachedTrendingRepos() method call succeeds
    * apiTrendingRepos() method call will never take place.
    *
    * The firstOrError() method call convert it to a Single<>
    * */
    fun getTrendingRepos(): Single<List<Repo>> =
            Maybe.concat(cachedTrendingRepos(),
                    apiTrendingRepos())
                    .firstOrError()

    fun getTrendingRepo(repoName: String, repoOwner: String): Single<Repo> =
            Maybe.concat(cachedRepo(repoName, repoOwner)
                    , apiRepo(repoName, repoOwner))
                    .firstOrError()

    private fun cachedTrendingRepos() =
            Maybe.create<List<Repo>> {
                if (!cachedTrendingRepos.isEmpty()) {
                    it.onSuccess(cachedTrendingRepos)
                }
                // Finish without emitting any item
                it.onComplete()
            }


    private fun apiTrendingRepos() =
            repoRequesterProvider.get().getTrendingRepos()
                    .doOnSuccess {
                        cachedTrendingRepos.clear()
                        cachedTrendingRepos.addAll(it)
                    }
                    .toMaybe()

    private fun cachedRepo(repoName: String, repoOwner: String): Maybe<Repo> =
            Maybe.create<Repo> {
                val repo: Repo? = cachedTrendingRepos
                        .filter { it.name == repoName }
                        .firstOrNull { it.owner.login == repoOwner }
                repo?.run { it.onSuccess(this) } ?: it.onComplete()
            }

    private fun apiRepo(repoName: String, repoOwner: String): Maybe<Repo> =
            repoRequesterProvider
                    .get()
                    .getTrendingRepo(repoName, repoOwner)
                    .toMaybe()
}
