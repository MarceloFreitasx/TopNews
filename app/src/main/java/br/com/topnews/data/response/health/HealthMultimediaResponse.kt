package br.com.topnews.data.response.health

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HealthMultimediaResponse(
    val url: String,
    val caption: String,
    val copyright: String
)