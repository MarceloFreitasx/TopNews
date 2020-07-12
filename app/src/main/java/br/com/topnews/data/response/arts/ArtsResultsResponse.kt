package br.com.topnews.data.response.arts

import br.com.topnews.data.models.ArtsModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtsResultsResponse(
    val title: String,
    val url: String,
    val byline: String,
    @Json(name = "multimedia")
    val multimedia: List<ArtsMultimediaResponse>
) {
    fun fromJson() = ArtsModel(
        title = this.title,
        byline = this.byline,
        cover = this.multimedia[0].url,
        url = this.url
    )
}