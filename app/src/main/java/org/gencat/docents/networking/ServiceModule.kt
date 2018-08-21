package org.gencat.docents.networking

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Call
import org.gencat.docents.model.AdapterFactory
import org.gencat.docents.model.ZonedDateTimeAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ServiceModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(AdapterFactory.INSTANCE)
            .add(ZonedDateTimeAdapter())
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(moshi: Moshi, callFactory: Call.Factory, @Named("base_url") baseUrl: String): Retrofit =
            Retrofit.Builder()
                    .callFactory(callFactory)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build()

}