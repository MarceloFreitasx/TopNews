package br.com.topnews.data.local.db.science

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.topnews.data.models.ScienceModel

@Dao
interface ScienceDAO {
    @Query("SELECT * FROM ScienceModel")
    fun getAllNews(): List<ScienceModel>

    @Query("SELECT * FROM ScienceModel WHERE uri = :uri")
    fun getNewsById(uri: String): ScienceModel

    @Insert
    fun insert(news: ScienceModel)

    @Query("DELETE FROM ScienceModel")
    fun deleteAll()
}