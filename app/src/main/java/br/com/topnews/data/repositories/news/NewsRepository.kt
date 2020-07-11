package br.com.topnews.data.repositories.news

import br.com.topnews.data.result.NewsResult

interface NewsRepository {
    fun getNews(resultCallback: (result: NewsResult) -> Unit)
}