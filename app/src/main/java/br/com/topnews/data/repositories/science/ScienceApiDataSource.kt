package br.com.topnews.data.repositories.science

import br.com.topnews.data.ApiService
import br.com.topnews.data.models.ScienceModel
import br.com.topnews.data.response.science.ScienceBodyResponse
import br.com.topnews.data.result.ScienceResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ScienceApiDataSource : ScienceRepository {

    override fun getScience(resultCallback: (result: ScienceResult) -> Unit) {
        ApiService.service.getScienceNews().enqueue(object : Callback<ScienceBodyResponse> {
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