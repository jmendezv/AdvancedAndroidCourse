package org.gencat.docents.trending

import org.gencat.docents.data.RepoRepository
import org.gencat.docents.data.RepoRequester
import org.gencat.docents.di.ScreenScope
import org.gencat.docents.model.Repo
import org.gencat.docents.ui.ScreenNavigator
import javax.inject.Inject

@ScreenScope

class TrendingReposPresenter @Inject constructor(val viewModel: TrendingReposViewModel,
                                                 val repoRepository: RepoRepository,
                                                 val screenNavigator: ScreenNavigator) :
        RepoAdapter.RepoClickedListener {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        repoRepository.getTrendingRepos()
                .doOnSubscribe {
                    viewModel.setLoading().accept(true)
                }
                // on any event
                .doOnEvent { data, throwable ->
                    viewModel.setLoading().accept(false)
                }
                // onSuccess(), onError()
                .subscribe(viewModel.setRepos(), viewModel.onError())
    }

    /* This will take to a detail screen */
    override fun onRepoClicked(repo: Repo) {
        screenNavigator.goToRepoDetails(repo.name, repo.owner.login)
    }

}