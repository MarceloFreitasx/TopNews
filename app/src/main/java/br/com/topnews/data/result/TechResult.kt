package br.com.topnews.data.result

import br.com.topnews.data.models.TechModel

sealed class TechResult {
    class Success(val news: List<TechModel>) : TechResult()
    class Error() : TechResult()
}