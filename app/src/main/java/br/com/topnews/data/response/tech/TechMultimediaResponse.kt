package br.com.topnews.data.response.tech

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TechMultimediaResponse(
    val url: String,
    val caption: String,
    val copyright: String
)