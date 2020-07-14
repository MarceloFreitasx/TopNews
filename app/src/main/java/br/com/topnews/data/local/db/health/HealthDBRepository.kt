package br.com.topnews.data.local.db.health

import android.os.AsyncTask
import br.com.topnews.TopNews
import br.com.topnews.data.models.HealthModel

class HealthDBRepository {
    fun getAllNews(): List<HealthModel>? {
        return get().execute().get()
    }

    fun findNews(newsModel: HealthModel): Boolean? {
        return find(newsModel).execute().get()
    }

    fun insertNews(newsModel: HealthModel) {
        insert(newsModel).execute()
    }

    fun removeAllNews() {
        removeAll().execute()
    }

    class get : AsyncTask<Void, Void, List<HealthModel>>() {
        override fun doInBackground(vararg p0: Void?): List<HealthModel>? {
            return TopNews.database?.healthDao()?.getAllNews()
        }
    }

    class insert(val model: HealthModel) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.healthDao()?.insert(model)
            return null
        }
    }

    class find(val model: HealthModel) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg p0: Void?): Boolean? {
            if (TopNews.database?.healthDao()?.getNewsById(model.uri) != null) {
                return true
            }
            return false
        }
    }

    class removeAll : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.healthDao()?.deleteAll()
            return null
        }
    }
}