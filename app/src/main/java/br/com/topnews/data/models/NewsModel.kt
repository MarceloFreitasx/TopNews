package br.com.topnews.data.models

data class NewsModel(
    val title: String,
    val byline: String,
    val cover: String,
    val url: String,
    val lido: Boolean = false
)