package br.com.topnews.di.modules

import br.com.topnews.data.repositories.tech.TechApiDataSource
import br.com.topnews.data.repositories.tech.TechRepository
import dagger.Module
import dagger.Provides

@Module
class TechModule {

    @Provides
    fun provideApiService(): TechRepository {
        return TechApiDataSource()
    }

}