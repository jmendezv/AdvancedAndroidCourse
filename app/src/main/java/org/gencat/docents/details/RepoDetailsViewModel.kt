package org.gencat.docents.details

import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.functions.Consumer
import org.gencat.docents.R
import org.gencat.docents.di.ScreenScope
import org.gencat.docents.model.Contributor
import org.gencat.docents.model.Repo
import org.threeten.bp.format.DateTimeFormatter
import timber.log.Timber
import javax.inject.Inject

@ScreenScope
class RepoDetailsViewModel @Inject constructor() {

    private val detailStateRelay: BehaviorRelay<RepoDetailsState> = BehaviorRelay.create()
    private val contributorStateRelay: BehaviorRelay<ContributorState> = BehaviorRelay.create()

    init {
        detailStateRelay.accept(RepoDetailsState.Builder().loading(true).build())
        contributorStateRelay.accept(ContributorState.Builder().loading(true).build())
    }

    fun details(): Observable<RepoDetailsState> = detailStateRelay

    fun contributors(): Observable<ContributorState> = contributorStateRelay

    fun processRepo(): Consumer<Repo> =
            Consumer {
                detailStateRelay.accept(
                        RepoDetailsState.Builder()
                                .loading(false)
                                .name(it.name)
                                .description(it.description)
                                .createdDate(it.createdDate.format(DATE_FORMATTER))
                                .updatedDate(it.updatedTime.format(DATE_FORMATTER))
                                .build()
                )
            }

    fun processContributors(): Consumer<List<Contributor>> =
            Consumer {
                contributorStateRelay.accept(
                        ContributorState.Builder()
                                .loading(false)
                                .contributors(it)
                                .build()
                )
            }

    fun detailsError(): Consumer<Throwable> =
            Consumer {
                Timber.e(it, "Error loading repo details")
                detailStateRelay.accept(
                        RepoDetailsState.Builder()
                                .loading(false)
                                .errorRes(R.string.api_error_single_repo)
                                .build()
                )
            }

    fun contributorsError(): Consumer<Throwable> =
            Consumer {
                Timber.e(it, "Error loading contributors")
                contributorStateRelay.accept(
                        ContributorState.Builder()
                                .loading(false)
                                .errorRes(R.string.api_error_contributors)
                                .build()
                )
            }

    private companion object {
        val DATE_FORMATTER: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    }
}