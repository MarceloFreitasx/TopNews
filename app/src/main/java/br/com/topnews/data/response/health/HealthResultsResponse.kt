package br.com.topnews.data.response.health

import br.com.topnews.data.models.HealthModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HealthResultsResponse(
    val title: String,
    val url: String,
    val byline: String,
    val uri: String,
    @Json(name = "multimedia")
    val multimedia: List<HealthMultimediaResponse>
) {
    fun fromJson() = HealthModel(
        title = this.title,
        byline = this.byline,
        cover = this.multimedia[0].url,
        url = this.url,
        uri = this.uri
    )
}