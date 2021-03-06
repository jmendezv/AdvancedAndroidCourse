package org.gencat.docents.model

import com.squareup.moshi.JsonAdapter
import se.ansman.kotshi.KotshiJsonAdapterFactory


@KotshiJsonAdapterFactory
abstract class AdapterFactory : JsonAdapter.Factory {
    companion object {
        val INSTANCE: AdapterFactory = KotshiAdapterFactory()
    }
}