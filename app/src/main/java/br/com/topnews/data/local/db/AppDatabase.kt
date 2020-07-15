package br.com.topnews.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.topnews.data.local.db.arts.ArtsDAO
import br.com.topnews.data.local.db.health.HealthDAO
import br.com.topnews.data.local.db.home.HomeDAO
import br.com.topnews.data.local.db.science.ScienceDAO
import br.com.topnews.data.local.db.tech.TechDAO
import br.com.topnews.data.models.*

@Database(
    entities = arrayOf(
        ArtsModel::class,
        HealthModel::class,
        NewsModel::class,
        ScienceModel::class,
        TechModel::class
    ),
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun artsDao(): ArtsDAO
    abstract fun healthDao(): HealthDAO
    abstract fun homeDao(): HomeDAO
    abstract fun scienceDao(): ScienceDAO
    abstract fun techDao(): TechDAO
}