package br.com.topnews.data.response.science

import br.com.topnews.data.models.ScienceModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ScienceResultsResponse(
    val title: String,
    val url: String,
    val byline: String,
    val uri: String,
    @Json(name = "multimedia")
    val multimedia: List<ScienceMultimediaResponse>
) {
    fun fromJson() = ScienceModel(
        title = this.title,
        byline = this.byline,
        cover = this.multimedia[0].url,
        url = this.url,
        uri = this.uri
    )
}