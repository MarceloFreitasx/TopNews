package br.com.topnews.di.components

import br.com.topnews.di.modules.TechModule
import br.com.topnews.presentation.tech.TechViewModel
import dagger.Component

@Component(modules = [TechModule::class])
interface TechComponent {
    fun inject(service: TechViewModel)
}