package br.com.topnews.di.components

import br.com.topnews.di.modules.ArtsModule
import br.com.topnews.presentation.arts.ArtsViewModel
import dagger.Component

@Component(modules = [ArtsModule::class])
interface ArtsComponent {
    fun inject(service: ArtsViewModel)
}