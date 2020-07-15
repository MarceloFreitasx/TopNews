package br.com.topnews.data.repositories.science

import br.com.topnews.data.ApiService
import br.com.topnews.data.models.ScienceModel
import br.com.topnews.data.response.science.ScienceBodyResponse
import br.com.topnews.data.result.ScienceResult
import br.com.topnews.di.components.DaggerApiComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ScienceApiDataSource : ScienceRepository {

    @Inject
    lateinit var apiService: ApiService

    init {
        DaggerApiComponent.create().inject(this)
    }

    override fun getScience(resultCallback: (result: ScienceResult) -> Unit) {
        apiService.service.getScienceNews().enqueue(object : Callback<ScienceBodyResponse> {
            override fun onResponse(
                call: Call<ScienceBodyResponse>,
                response: Response<ScienceBodyResponse>
            ) {
                when {
                    response.isSuccessful -> {
                        val news: MutableList<ScienceModel> = mutableListOf()
                        response.body()?.let { artsBodyResponse ->
                            for (result in artsBodyResponse.result) {
                                val new = result.fromJson()
                                news.add(new)
                            }
                        }
                        resultCallback(ScienceResult.Success(news))
                    }
                    else -> {
                        resultCallback(ScienceResult.Error())
                    }
                }
            }

            override fun onFailure(call: Call<ScienceBodyResponse>, t: Throwable) {
                resultCallback(ScienceResult.Error())
            }
        })
    }

}