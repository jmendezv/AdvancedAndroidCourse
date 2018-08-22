package org.gencat.docents.details

import io.reactivex.functions.Consumer
import org.gencat.docents.data.RepoRepository
import org.gencat.docents.di.ScreenScope
import javax.inject.Inject
import javax.inject.Named

@ScreenScope
class RepoDetailsPresenter @Inject constructor(
        @Named("repo_name") val repoName: String,
        @Named("repo_owner") val repoOwner: String,
        val repoRepository: RepoRepository,
        val repoDetailsViewModel: RepoDetailsViewModel) {

    init {
        repoRepository.getTrendingRepo(repoName, repoOwner)
                .doOnSuccess(repoDetailsViewModel.processRepo())
                .doOnError(repoDetailsViewModel.detailsError())
                .flatMap {
                    repoRepository.getContributors(it.contributorsUrl)
                            .doOnError(repoDetailsViewModel.contributorsError())
                }
                .subscribe(repoDetailsViewModel.processContributors(), Consumer {
                    // we handle loggin in the view model directly
                } )
    }


}