package br.com.topnews.di.components

import br.com.topnews.di.modules.HealthModule
import br.com.topnews.presentation.health.HealthViewModel
import dagger.Component

@Component(modules = [HealthModule::class])
interface HealthComponent {
    fun inject(service: HealthViewModel)
}