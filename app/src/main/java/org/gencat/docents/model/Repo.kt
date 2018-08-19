package org.gencat.docents.model

import com.squareup.moshi.Json
import org.threeten.bp.ZonedDateTime
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Repo(
        val id: Long,
        val name: String,
        val description: String,
        val owner: User,
        @Json(name = "stargazers_count")
        val stargazersCount: Long,
        @Json(name = "forks_count")
        val forksCount: Long,
        @Json(name = "contributors_url")
        val contributorsUrl: String,
        @Json(name = "created_at")
        val createdDate: ZonedDateTime,
        @Json(name = "updated_at")
        val updatedTime: ZonedDateTime)