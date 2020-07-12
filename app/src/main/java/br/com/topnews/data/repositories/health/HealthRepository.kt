package br.com.topnews.data.repositories.health

import br.com.topnews.data.result.HealthResult

interface HealthRepository {
    fun getHealth(resultCallback: (result: HealthResult) -> Unit)
}