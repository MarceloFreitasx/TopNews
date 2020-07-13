package br.com.topnews

import android.app.Application
import br.com.topnews.data.local.db.AppDatabase
import br.com.topnews.data.local.db.DbService

open class TopNews : Application() {

    companion object {
        var database: AppDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        database = DbService.initDb(this)
    }
}