package br.com.topnews.data.response.news

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NewsMultimediaResponse(
    val url: String,
    val caption: String,
    val copyright: String
)