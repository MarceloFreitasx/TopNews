package br.com.topnews.data.response.arts

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtsBodyResponse(
    @Json(name = "results")
    val result: List<ArtsResultsResponse>
)