package br.com.topnews.data.response.arts

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtsMultimediaResponse(
    val url: String,
    val caption: String,
    val copyright: String
)