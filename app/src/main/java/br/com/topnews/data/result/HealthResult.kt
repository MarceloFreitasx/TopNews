package br.com.topnews.data.result

import br.com.topnews.data.models.HealthModel

sealed class HealthResult {
    class Success(val news: List<HealthModel>) : HealthResult()
    class Error() : HealthResult()
}