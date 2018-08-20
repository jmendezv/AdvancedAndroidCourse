package org.gencat.docents.data

import io.reactivex.Single
import org.gencat.docents.model.TrendingReposResponse
import retrofit2.http.GET

/*
* In Rx a Single is a stream that will only emit one item or an error
* Observables on the other hand can emit multiple items or an error
*
* */
interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    fun getTrendingRepos(): Single<TrendingReposResponse>
}