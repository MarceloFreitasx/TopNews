package br.com.topnews.data.repositories.health

import br.com.topnews.data.ApiService
import br.com.topnews.data.models.HealthModel
import br.com.topnews.data.response.health.HealthBodyResponse
import br.com.topnews.data.result.HealthResult
import br.com.topnews.di.components.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HealthApiDataSource : HealthRepository {

    @Inject
    lateinit var apiService: ApiService

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun getHealth(resultCallback: (result: HealthResult) -> Unit) {
        apiService.service.getHealthNews().enqueue(object : Callback<HealthBodyResponse> {
            override fun onResponse(
                call: Call<HealthBodyResponse>,
                response: Response<HealthBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val news: MutableList<HealthModel> = mutableListOf()
                        response.body()?.let { artsBodyResponse ->
                            for (result in artsBodyResponse.result) {
                                val new = result.fromJson()
                                news.add(new)
                            }
                        }
                        resultCallback(HealthResult.Success(news))
                    }
                    else -> {
                        resultCallback(HealthResult.Error())
                    }
                }
            }

            override fun onFailure(call: Call<HealthBodyResponse>, t: Throwable) {
                resultCallback(HealthResult.Error())
            }
        })
    }

}