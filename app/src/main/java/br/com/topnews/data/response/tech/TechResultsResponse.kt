package br.com.topnews.data.response.tech

import br.com.topnews.data.models.NewsModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TechResultsResponse(
    val title: String,
    val url: String,
    val byline: String,
    @Json(name = "multimedia")
    val multimedia: List<TechMultimediaResponse>
) {
    fun fromJson() = NewsModel(
        title = this.title,
        byline = this.byline,
        cover = this.multimedia[0].url,
        url = this.url
    )
}