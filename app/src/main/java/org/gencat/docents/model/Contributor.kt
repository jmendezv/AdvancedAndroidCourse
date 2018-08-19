package org.gencat.docents.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
class Contributor(
        val id: Long,
        val login: String,
        @Json(name = "avatar_url") val avatarUrl: String)