package br.com.topnews.data.response.science

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScienceBodyResponse(
    @Json(name = "results")
    val result: List<ScienceResultsResponse>
)