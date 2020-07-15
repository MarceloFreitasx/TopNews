package br.com.topnews.di.components

import br.com.topnews.data.ApiService
import br.com.topnews.data.repositories.arts.ArtsApiDataSource
import br.com.topnews.data.repositories.health.HealthApiDataSource
import br.com.topnews.data.repositories.news.NewsApiDataSource
import br.com.topnews.data.repositories.science.ScienceApiDataSource
import br.com.topnews.data.repositories.tech.TechApiDataSource
import br.com.topnews.di.modules.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service: ApiService)
    fun inject(artsApiDataSource: ArtsApiDataSource)
    fun inject(healthApiDataSource: HealthApiDataSource)
    fun inject(newsApiDataSource: NewsApiDataSource)
    fun inject(scienceApiDataSource: ScienceApiDataSource)
    fun inject(techApiDataSource: TechApiDataSource)
}