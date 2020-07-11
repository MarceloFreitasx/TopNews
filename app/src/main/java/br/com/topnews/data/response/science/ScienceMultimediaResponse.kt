package br.com.topnews.data.response.science

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScienceMultimediaResponse(
    val url: String,
    val caption: String,
    val copyright: String
)