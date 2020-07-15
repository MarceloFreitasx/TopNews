package br.com.topnews.di.modules

import br.com.topnews.data.repositories.science.ScienceApiDataSource
import br.com.topnews.data.repositories.science.ScienceRepository
import dagger.Module
import dagger.Provides

@Module
class ScienceModule {

    @Provides
    fun provideApiService(): ScienceRepository {
        return ScienceApiDataSource()
    }

}