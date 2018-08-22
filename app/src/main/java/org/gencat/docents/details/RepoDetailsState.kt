package org.gencat.docents.details

/* Builder pattern not needed in Kotlin, but here it goes anyway */
data class RepoDetailsState constructor(val loading: Boolean = false,
                                        val name: String? = null,
                                        val description: String? = null,
                                        val createdDate: String? = null,
                                        val updatedDate: String? = null,
                                        val errorRes: Int = -1) {

    fun isSuccess(): Boolean = errorRes == -1


    class Builder {
        private var loading: Boolean = false
        private var name: String? = null
        private var description: String? = null
        private var createdDate: String? = null
        private var updatedDate: String? = null
        private var errorRes: Int = -1

        fun loading(loading: Boolean) = apply { this.loading = loading }
        fun name(name: String? = null) = apply { this.name = name }
        fun description(description: String? = null) = apply { this.description = description }
        fun createdDate(createdDate: String? = null) = apply { this.createdDate = createdDate }
        fun updatedDate(updatedDate: String? = null) = apply { this.updatedDate = updatedDate }
        fun errorRes(errorRes: Int = -1) = apply { this.errorRes = errorRes }

        fun build() = RepoDetailsState(
                loading,
                name,
                description,
                createdDate,
                updatedDate,
                errorRes)

    }

}