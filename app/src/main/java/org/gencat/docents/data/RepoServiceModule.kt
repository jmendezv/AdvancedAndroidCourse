package org.gencat.docents.data

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RepoServiceModule {

    @Provides
    @Singleton
    fun provideRepoService(retrofit: Retrofit): RepoService =
            retrofit.create(RepoService::class.java)
}