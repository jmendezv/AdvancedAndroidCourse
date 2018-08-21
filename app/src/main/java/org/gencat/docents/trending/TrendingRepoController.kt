package org.gencat.docents.trending

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.gencat.docents.R
import org.gencat.docents.base.BaseController
import javax.inject.Inject

class TrendingRepoController : BaseController() {

    @Inject
    lateinit var presenter: TrendingReposPresenter
    @Inject
    lateinit var viewModel: TrendingReposViewModel

    @BindView(R.id.repo_list)
    lateinit var repoList: RecyclerView
    @BindView(R.id.loading_indicator)
    lateinit var loadingView: View
    @BindView(R.id.tv_error)
    lateinit var errorText: TextView

    override fun onViewBound(view: View) {
        repoList.layoutManager = LinearLayoutManager(view.context)
        repoList.adapter = RepoAdapter(presenter)
    }

    override fun subscriptions(): Array<Disposable> =
            arrayOf(
                    viewModel.getLoading()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {
                                loadingView.visibility = if (it) View.VISIBLE else View.GONE
                                repoList.visibility = if (it) View.GONE else View.VISIBLE
                                errorText.visibility = if (it) View.GONE else errorText.visibility
                            },
                    viewModel.getRepos()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe((repoList.adapter as RepoAdapter)::setData),
                viewModel.getError()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe {
                            if (it.toInt() == -1) {
                                errorText.setText(null)
                                errorText.visibility = View.GONE
                            }
                            else {
                                errorText.visibility = View.VISIBLE
                                repoList.visibility = View.GONE
                                errorText.setText(it.toInt())
                            }
                        }
            )

    override fun layoutRes(): Int = R.layout.screen_trending_repos

}