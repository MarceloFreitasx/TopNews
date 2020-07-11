package br.com.topnews.data

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiService {
    private fun initRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.nytimes.com/svc/topstories/v2/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    val service: ApiInterface = initRetrofit().create(
        ApiInterface::class.java)
}