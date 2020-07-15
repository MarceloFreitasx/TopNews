package br.com.topnews.di.modules

import br.com.topnews.presentation.HomeViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HomeModule {

    @Provides
    fun provideApiService(): HomeViewModel {
        return HomeViewModel()
    }

}