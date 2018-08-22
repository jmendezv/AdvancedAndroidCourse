package org.gencat.docents.details

import org.gencat.docents.model.Contributor

data class ContributorState public constructor(
        val loading: Boolean = false,
        val contributors: List<Contributor>? = null,
        val errorRes: Int = -1) {

    class Builder {
        private var loading: Boolean = false
        private var contributors: List<Contributor>? = null
        private var errorRes: Int = -1

        fun loading(loading: Boolean = false) = apply { this.loading = loading }
        fun contributors(contributors: List<Contributor>? = null) = apply { this.contributors = contributors }
        fun errorRes(errorRes: Int = -1) = apply { this.errorRes = errorRes }

        fun build() = ContributorState(loading, contributors, errorRes)
    }

}