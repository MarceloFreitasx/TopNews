package br.com.topnews.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class ArtsModel(
    @PrimaryKey val uri: String,
    val title: String,
    val byline: String,
    val cover: String,
    val url: String,
    val lido: Boolean = false
)
