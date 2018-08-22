package org.gencat.docents.details

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.bluelinelabs.conductor.Controller
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.gencat.docents.R
import org.gencat.docents.base.BaseController
import javax.inject.Inject

class RepoDetailsController(bundle: Bundle) : BaseController(bundle) {

    @Inject
    lateinit var repoDetailsViewModel: RepoDetailsViewModel

    @Inject
    lateinit var repoDetailsPresenter: RepoDetailsPresenter

    @BindView(R.id.tv_repo_name)
    lateinit var repoNameText: TextView
    @BindView(R.id.tv_repo_description)
    lateinit var repoDescriptionText: TextView
    @BindView(R.id.tv_creation_date)
    lateinit var createdDateText: TextView
    @BindView(R.id.tv_updated_date)
    lateinit var updatedDateText: TextView
    @BindView(R.id.contributor_list)
    lateinit var contributorList: RecyclerView
    @BindView(R.id.loading_indicator)
    lateinit var detailsLoadingView: View
    @BindView(R.id.contributor_loading_indicator)
    lateinit var contributorsLoadingView: View
    @BindView(R.id.content)
    lateinit var contentContainer: View
    @BindView(R.id.tv_error)
    lateinit var errorText: TextView
    @BindView(R.id.tv_contributors_error)
    lateinit var contributorsErrorText: TextView

    override fun onViewBound(view: View) {
        super.onViewBound(view)
        contributorList.layoutManager = LinearLayoutManager(view.context)
        contributorList.adapter = ContributorAdapter()
    }

    override fun subscriptions(): Array<Disposable> =
            arrayOf(
                    repoDetailsViewModel.details()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {

                                if (it.loading) {
                                    detailsLoadingView.visibility = View.VISIBLE
                                    contentContainer.visibility = View.GONE
                                    errorText.visibility = View.GONE
                                    errorText.text = null
                                } else {
                                    if (it.isSuccess()) {
                                        errorText.text = null
                                    } else {
                                        errorText.setText(it.errorRes)
                                    }
                                    detailsLoadingView.visibility = View.GONE
                                    contentContainer.visibility = if (it.isSuccess()) View.VISIBLE else View.GONE
                                    errorText.visibility = if (it.isSuccess()) View.GONE else View.VISIBLE
                                    repoNameText.setText(it.name)
                                    repoDescriptionText.setText(it.description)
                                    createdDateText.setText(it.createdDate)
                                    updatedDateText.setText(it.updatedDate)
                                }

                            },
                    repoDetailsViewModel.contributors()
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe {

                                if (it.loading) {
                                    contributorsLoadingView.visibility = View.VISIBLE
                                    contributorList.visibility = View.GONE
                                    contributorsErrorText.visibility = View.GONE
                                    contributorsErrorText.text = null
                                } else {
                                    contributorsLoadingView.visibility = View.GONE
                                    contributorList.visibility = if (it.isSuccess()) View.VISIBLE else View.GONE
                                    contributorsErrorText.visibility = if (it.isSuccess()) View.GONE else View.VISIBLE
                                    if (it.isSuccess()) {
                                        contributorsErrorText.text = null
                                        (contributorList.adapter as ContributorAdapter).setData(it.contributors)
                                    } else {
                                        contributorsErrorText.setText(it.errorRes)
                                    }
                                }

                            }
            )

    override fun layoutRes(): Int = R.layout.screen_repo_details

    companion object {

        const val REPO_NAME_KEY = "repo_name"
        const val REPO_OWNER_KEY = "repo_owner"

        fun newInstance(repoName: String, repoOwner: String): Controller {
            val bundle = Bundle()
            bundle.putString(REPO_NAME_KEY, repoName)
            bundle.putString(REPO_OWNER_KEY, repoOwner)
            return RepoDetailsController(bundle)
        }

    }

}