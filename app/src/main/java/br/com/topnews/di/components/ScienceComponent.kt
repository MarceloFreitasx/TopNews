package br.com.topnews.di.components

import br.com.topnews.di.modules.ScienceModule
import br.com.topnews.presentation.science.ScienceViewModel
import dagger.Component

@Component(modules = [ScienceModule::class])
interface ScienceComponent {
    fun inject(service: ScienceViewModel)
}