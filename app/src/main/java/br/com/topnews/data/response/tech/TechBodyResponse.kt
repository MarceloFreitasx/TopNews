package br.com.topnews.data.response.tech

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TechBodyResponse(
    @Json(name = "results")
    val result: List<TechResultsResponse>
)