package br.com.topnews.data.result

import br.com.topnews.data.models.ArtsModel

sealed class ArtsResult {
    class Success(val news: List<ArtsModel>) : ArtsResult()
    class Error() : ArtsResult()
}