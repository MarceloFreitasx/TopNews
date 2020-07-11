package br.com.topnews.data.response.health

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HealthBodyResponse(
    @Json(name = "results")
    val result: List<HealthResultsResponse>
)