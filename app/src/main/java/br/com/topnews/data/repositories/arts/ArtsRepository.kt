package br.com.topnews.data.repositories.arts

import br.com.topnews.data.result.ArtsResult

interface ArtsRepository {
    fun getArts(resultCallback: (result: ArtsResult) -> Unit)
}