package br.com.topnews.data.local.db.tech

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.topnews.data.models.TechModel

@Dao
interface TechDAO {
    @Query("SELECT * FROM TechModel")
    fun getAllNews(): List<TechModel>

    @Query("SELECT * FROM TechModel WHERE uri = :uri")
    fun getNewsById(uri: String): TechModel

    @Insert
    fun insert(news: TechModel)

    @Query("DELETE FROM TechModel")
    fun deleteAll()
}