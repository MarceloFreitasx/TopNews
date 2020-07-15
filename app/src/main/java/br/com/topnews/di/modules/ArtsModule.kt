package br.com.topnews.di.modules

import br.com.topnews.data.repositories.arts.ArtsApiDataSource
import br.com.topnews.data.repositories.arts.ArtsRepository
import dagger.Module
import dagger.Provides

@Module
class ArtsModule {

    @Provides
    fun provideApiService(): ArtsRepository {
        return ArtsApiDataSource()
    }

}