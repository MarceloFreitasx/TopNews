package br.com.topnews.di.modules

import br.com.topnews.data.repositories.health.HealthApiDataSource
import br.com.topnews.data.repositories.health.HealthRepository
import dagger.Module
import dagger.Provides

@Module
class HealthModule {

    @Provides
    fun provideApiService(): HealthRepository {
        return HealthApiDataSource()
    }

}