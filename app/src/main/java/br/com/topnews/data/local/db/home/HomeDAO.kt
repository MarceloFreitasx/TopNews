package br.com.topnews.data.local.db.home

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.topnews.data.models.NewsModel

@Dao
interface HomeDAO {
    @Query("SELECT * FROM NewsModel")
    fun getAllNews(): List<NewsModel>

    @Query("SELECT * FROM NewsModel WHERE uri = :uri")
    fun getNewsById(uri: String): NewsModel

    @Insert
    fun insert(news: NewsModel)

    @Query("DELETE FROM NewsModel")
    fun deleteAll()
}