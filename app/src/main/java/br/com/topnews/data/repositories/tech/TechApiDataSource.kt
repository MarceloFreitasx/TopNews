package br.com.topnews.data.repositories.tech

import br.com.topnews.data.ApiService
import br.com.topnews.data.models.TechModel
import br.com.topnews.data.response.tech.TechBodyResponse
import br.com.topnews.data.result.TechResult
import br.com.topnews.di.components.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TechApiDataSource : TechRepository {

    @Inject
    lateinit var apiService: ApiService

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun getTech(resultCallback: (result: TechResult) -> Unit) {
        apiService.service.getTechNews().enqueue(object : Callback<TechBodyResponse> {
            override fun onResponse(
                call: Call<TechBodyResponse>,
                response: Response<TechBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val news: MutableList<TechModel> = mutableListOf()
                        response.body()?.let { artsBodyResponse ->
                            for (result in artsBodyResponse.result) {
                                val new = result.fromJson()
                                news.add(new)
                            }
                        }
                        resultCallback(TechResult.Success(news))
                    }
                    else -> {
                        resultCallback(TechResult.Error())
                    }
                }
            }

            override fun onFailure(call: Call<TechBodyResponse>, t: Throwable) {
                resultCallback(TechResult.Error())
            }
        })
    }

}