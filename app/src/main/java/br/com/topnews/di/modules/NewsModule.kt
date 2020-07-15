package br.com.topnews.di.modules

import br.com.topnews.data.repositories.news.NewsApiDataSource
import br.com.topnews.data.repositories.news.NewsRepository
import dagger.Module
import dagger.Provides

@Module
class NewsModule {

    @Provides
    fun provideApiService(): NewsRepository {
        return NewsApiDataSource()
    }

}