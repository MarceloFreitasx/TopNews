package br.com.topnews.data.local.db.health

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import br.com.topnews.data.models.ArtsModel
import br.com.topnews.data.models.HealthModel

@Dao
interface HealthDAO {
    @Query("SELECT * FROM HealthModel")
    fun getAllNews(): List<HealthModel>

    @Query("SELECT * FROM HealthModel WHERE uri = :uri")
    fun getNewsById(uri: String): HealthModel

    @Insert
    fun insert(news: HealthModel)

    @Query("DELETE FROM HealthModel")
    fun deleteAll()
}