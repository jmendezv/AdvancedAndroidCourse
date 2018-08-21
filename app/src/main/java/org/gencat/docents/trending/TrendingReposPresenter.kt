package org.gencat.docents.trending

import org.gencat.docents.data.RepoRequester
import org.gencat.docents.di.ScreenScope
import javax.inject.Inject

@ScreenScope
class TrendingReposPresenter @Inject constructor(val viewModel: TrendingReposViewModel, val repoRequester: RepoRequester) {

    init {
        loadRepos()
    }

    private fun loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe {
                    viewModel.setLoading().accept(true)
                }
                // on any event
                .doOnEvent {
                    data, throwable -> viewModel.setLoading().accept(false)
                }
                // onSuccess(), onError()
                .subscribe(viewModel.setRepos(), viewModel.onError())
    }

    // 4/23/8'21''
}