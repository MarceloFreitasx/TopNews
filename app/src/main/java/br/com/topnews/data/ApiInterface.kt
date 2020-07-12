package br.com.topnews.data

import br.com.topnews.data.response.arts.ArtsBodyResponse
import br.com.topnews.data.response.health.HealthBodyResponse
import br.com.topnews.data.response.news.NewsBodyResponse
import br.com.topnews.data.response.science.ScienceBodyResponse
import br.com.topnews.data.response.tech.TechBodyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("arts.json")
    fun getArtsNews(
        @Query("api-key") apiKey: String = APIKEY
    ): Call<ArtsBodyResponse>

    @GET("home.json")
    fun getHomeNews(
        @Query("api-key") apiKey: String = APIKEY
    ): Call<NewsBodyResponse>

    @GET("science.json")
    fun getScienceNews(
        @Query("api-key") apiKey: String = APIKEY
    ): Call<ScienceBodyResponse>

    @GET("health.json")
    fun getHealthNews(
        @Query("api-key") apiKey: String = APIKEY
    ): Call<HealthBodyResponse>

    @GET("technology.json")
    fun getTechNews(
        @Query("api-key") apiKey: String = APIKEY
    ): Call<TechBodyResponse>

    companion object {
        const val APIKEY = "OwdGKXKNG1r6kClc0ULSGLAekhx1ZE7e"
    }

}