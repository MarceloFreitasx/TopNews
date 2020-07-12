package br.com.topnews.data.repositories.tech

import br.com.topnews.data.result.TechResult

interface TechRepository {
    fun getTech(resultCallback: (result: TechResult) -> Unit)
}