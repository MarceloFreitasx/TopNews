package br.com.topnews.data.repositories.news

import br.com.topnews.data.ApiService
import br.com.topnews.data.models.NewsModel
import br.com.topnews.data.response.news.NewsBodyResponse
import br.com.topnews.data.result.NewsResult
import br.com.topnews.di.components.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsApiDataSource : NewsRepository {

    @Inject
    lateinit var apiService: ApiService

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun getNews(resultCallback: (result: NewsResult) -> Unit) {
        apiService.service.getHomeNews().enqueue(object : Callback<NewsBodyResponse> {
            override fun onResponse(
                call: Call<NewsBodyResponse>,
                response: Response<NewsBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val news: MutableList<NewsModel> = mutableListOf()
                        response.body()?.let { newsBodyResponse ->
                            for (result in newsBodyResponse.result) {
                                val new = result.fromJson()
                                news.add(new)
                            }
                        }
                        resultCallback(NewsResult.Success(news))
                    }
                    else -> {
                        resultCallback(NewsResult.Error())
                    }
                }
            }

            override fun onFailure(call: Call<NewsBodyResponse>, t: Throwable) {
                resultCallback(NewsResult.Error())
            }
        })
    }

}