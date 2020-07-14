package br.com.topnews.data.local.db.science

import android.os.AsyncTask
import br.com.topnews.TopNews
import br.com.topnews.data.models.ScienceModel

class ScienceDBRepository {
    fun getAllNews(): List<ScienceModel>? {
        return get().execute().get()
    }

    fun findNews(newsModel: ScienceModel): Boolean? {
        return find(newsModel).execute().get()
    }

    fun insertNews(newsModel: ScienceModel) {
        insert(newsModel).execute()
    }

    fun removeAllNews() {
        removeAll().execute()
    }

    class get : AsyncTask<Void, Void, List<ScienceModel>>() {
        override fun doInBackground(vararg p0: Void?): List<ScienceModel>? {
            return TopNews.database?.scienceDao()?.getAllNews()
        }
    }

    class insert(val model: ScienceModel) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.scienceDao()?.insert(model)
            return null
        }
    }

    class find(val model: ScienceModel) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg p0: Void?): Boolean? {
            if (TopNews.database?.scienceDao()?.getNewsById(model.uri) != null) {
                return true
            }
            return false
        }
    }

    class removeAll : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.scienceDao()?.deleteAll()
            return null
        }
    }
}