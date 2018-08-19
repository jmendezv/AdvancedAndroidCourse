package org.gencat.docents.model

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import org.threeten.bp.ZonedDateTime

class ZonedDateTimeAdapter {

    @FromJson
    fun fromJson(json: String): ZonedDateTime = ZonedDateTime.parse(json)

    @ToJson
    fun toJson(value: ZonedDateTime?): String? = value?.toString() ?: null
}