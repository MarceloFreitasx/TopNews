package br.com.topnews.data.local.db.tech

import android.os.AsyncTask
import br.com.topnews.TopNews
import br.com.topnews.data.models.TechModel

class TechDBRepository {
    fun getAllNews(): List<TechModel>? {
        return get().execute().get()
    }

    fun findNews(newsModel: TechModel): Boolean? {
        return find(newsModel).execute().get()
    }

    fun insertNews(newsModel: TechModel) {
        insert(newsModel).execute()
    }

    fun removeAllNews() {
        removeAll().execute()
    }

    class get : AsyncTask<Void, Void, List<TechModel>>() {
        override fun doInBackground(vararg p0: Void?): List<TechModel>? {
            return TopNews.database?.techDao()?.getAllNews()
        }
    }

    class insert(val model: TechModel) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.techDao()?.insert(model)
            return null
        }
    }

    class find(val model: TechModel) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg p0: Void?): Boolean? {
            if (TopNews.database?.techDao()?.getNewsById(model.uri) != null) {
                return true
            }
            return false
        }
    }

    class removeAll : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.techDao()?.deleteAll()
            return null
        }
    }
}