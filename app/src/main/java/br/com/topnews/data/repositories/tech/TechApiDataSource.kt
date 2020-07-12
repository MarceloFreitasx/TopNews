package br.com.topnews.data.repositories.tech

import br.com.topnews.data.ApiService
import br.com.topnews.data.models.TechModel
import br.com.topnews.data.response.tech.TechBodyResponse
import br.com.topnews.data.result.TechResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechApiDataSource : TechRepository {

    override fun getTech(resultCallback: (result: TechResult) -> Unit) {
        ApiService.service.getTechNews().enqueue(object : Callback<TechBodyResponse> {
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