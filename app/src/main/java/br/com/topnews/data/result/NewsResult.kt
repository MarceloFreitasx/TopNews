package br.com.topnews.data.result

import br.com.topnews.data.models.NewsModel

sealed class NewsResult {
    class Success(val news: List<NewsModel>) : NewsResult()
    class Error() : NewsResult()
}