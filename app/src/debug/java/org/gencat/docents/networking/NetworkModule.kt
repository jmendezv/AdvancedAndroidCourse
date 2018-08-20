package org.gencat.docents.networking

import dagger.Module
import dagger.Provides
import okhttp3.Call
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): Call.Factory =
            OkHttpClient.Builder().build()

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String =
            "https://api.github.com/"

}