package br.com.topnews.data.local.db.arts

import android.os.AsyncTask
import br.com.topnews.TopNews
import br.com.topnews.data.models.ArtsModel

class ArtsDBRepository {
    fun getAllNews(): List<ArtsModel>? {
        return get().execute().get()
    }

    fun findNews(newsModel: ArtsModel): Boolean? {
        return find(newsModel).execute().get()
    }

    fun insertNews(newsModel: ArtsModel) {
        insert(newsModel).execute()
    }

    fun removeAllNews() {
        removeAll().execute()
    }

    class get : AsyncTask<Void, Void, List<ArtsModel>>() {
        override fun doInBackground(vararg p0: Void?): List<ArtsModel>? {
            return TopNews.database?.artsDao()?.getAllNews()
        }
    }

    class insert(val model: ArtsModel) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.artsDao()?.insert(model)
            return null
        }
    }

    class find(val model: ArtsModel) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg p0: Void?): Boolean? {
            if (TopNews.database?.artsDao()?.getNewsById(model.uri) != null) {
                return true
            }
            return false
        }
    }

    class removeAll : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.artsDao()?.deleteAll()
            return null
        }
    }
}