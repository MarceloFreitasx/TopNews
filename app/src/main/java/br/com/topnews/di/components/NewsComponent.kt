package br.com.topnews.di.components

import br.com.topnews.di.modules.NewsModule
import br.com.topnews.presentation.news.NewsViewModel
import dagger.Component

@Component(modules = [NewsModule::class])
interface NewsComponent {
    fun inject(service: NewsViewModel)
}