package br.com.topnews.data.repositories.science

import br.com.topnews.data.result.ScienceResult

interface ScienceRepository {
    fun getScience(resultCallback: (result: ScienceResult) -> Unit)
}