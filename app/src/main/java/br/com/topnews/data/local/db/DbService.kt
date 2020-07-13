package br.com.topnews.data.local.db

import android.content.Context
import androidx.room.Room

object DbService {

    fun initDb(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "topnews"
        ).fallbackToDestructiveMigration().build()
    }

}