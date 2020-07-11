package br.com.topnews.data.response.news

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsBodyResponse(
    @Json(name = "results")
    val result: List<NewsResultsResponse>
)