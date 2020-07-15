package br.com.topnews.data.repositories.arts

import br.com.topnews.data.ApiService
import br.com.topnews.data.models.ArtsModel
import br.com.topnews.data.response.arts.ArtsBodyResponse
import br.com.topnews.data.result.ArtsResult
import br.com.topnews.di.components.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ArtsApiDataSource : ArtsRepository {

    @Inject
    lateinit var apiService: ApiService

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun getArts(resultCallback: (result: ArtsResult) -> Unit) {
        apiService.service.getArtsNews().enqueue(object : Callback<ArtsBodyResponse> {
            override fun onResponse(
                call: Call<ArtsBodyResponse>,
                response: Response<ArtsBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val news: MutableList<ArtsModel> = mutableListOf()
                        response.body()?.let { artsBodyResponse ->
                            for (result in artsBodyResponse.result) {
                                val new = result.fromJson()
                                news.add(new)
                            }
                        }
                        resultCallback(ArtsResult.Success(news))
                    }
                    else -> {
                        resultCallback(ArtsResult.Error())
                    }
                }
            }

            override fun onFailure(call: Call<ArtsBodyResponse>, t: Throwable) {
                resultCallback(ArtsResult.Error())
            }
        })
    }

}