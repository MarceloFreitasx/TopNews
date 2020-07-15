package br.com.topnews.di.components

import br.com.topnews.di.modules.HomeModule
import br.com.topnews.presentation.HomeActivity
import br.com.topnews.presentation.HomeViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [HomeModule::class])
interface HomeComponent {
    fun inject(service: HomeActivity)
}