package br.com.topnews.data

import br.com.topnews.di.components.DaggerApiComponent
import javax.inject.Inject

class ApiService {
    @Inject
    lateinit var service: ApiInterface

    init {
        DaggerApiComponent.create().inject(this)
    }
}