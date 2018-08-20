package org.gencat.docents.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
class TrendingReposResponse(
        @Json(name = "items") val repos: List<Repo>)