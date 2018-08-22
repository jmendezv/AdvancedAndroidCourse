package org.gencat.docents.data

import io.reactivex.Maybe
import io.reactivex.Single
import org.gencat.docents.model.Contributor
import org.gencat.docents.model.Repo
import org.gencat.docents.model.TrendingReposResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

/*
* In Rx a Single is a stream that will only emit one item or an error
* Observables on the other hand can emit multiple items or an error
*
* */
interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    fun getTrendingRepos(): Single<TrendingReposResponse>

    @GET("repos/{owner}/{name}")
    fun getTrendingRepo(@Path("name")repoName: String, @Path("owner") repoOwner: String):
            Single<Repo>

    @GET fun getContributors(@Url url: String): Single<List<Contributor>>

}