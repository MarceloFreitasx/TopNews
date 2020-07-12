package br.com.topnews.data.result

import br.com.topnews.data.models.ScienceModel

sealed class ScienceResult {
    class Success(val news: List<ScienceModel>) : ScienceResult()
    class Error() : ScienceResult()
}