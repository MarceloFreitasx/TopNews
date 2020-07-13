package br.com.topnews.data.local.db.arts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.topnews.data.models.ArtsModel

@Dao
interface ArtsDAO {
    @Query("SELECT * FROM ArtsModel")
    fun getAllArtsNews(): List<ArtsModel>

    @Query("SELECT * FROM ArtsModel WHERE uri = :uri")
    fun getNewsById(uri: String): ArtsModel

    @Insert
    fun insert(news: ArtsModel)

    @Query("DELETE FROM ArtsModel")
    fun deleteAll()
}