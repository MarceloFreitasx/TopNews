package br.com.topnews.data.local.db.home

import android.os.AsyncTask
import br.com.topnews.TopNews
import br.com.topnews.data.models.NewsModel

class HomeRepository {
    fun getAllNews(): List<NewsModel>? {
        return get().execute().get()
    }

    fun findNews(newsModel: NewsModel): Boolean? {
        return find(newsModel).execute().get()
    }

    fun insertNews(newsModel: NewsModel) {
        insert(newsModel).execute()
    }

    fun removeAllNews() {
        removeAll().execute()
    }

    class get : AsyncTask<Void, Void, List<NewsModel>>() {
        override fun doInBackground(vararg p0: Void?): List<NewsModel>? {
            return TopNews.database?.homeDao()?.getAllNews()
        }
    }

    class insert(val model: NewsModel) : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.homeDao()?.insert(model)
            return null
        }
    }

    class find(val model: NewsModel) : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg p0: Void?): Boolean? {
            if (TopNews.database?.homeDao()?.getNewsById(model.uri) != null) {
                return true
            }
            return false
        }
    }

    class removeAll : AsyncTask<Void, Void, Void>() {
        override fun doInBackground(vararg p0: Void?): Void? {
            TopNews.database?.homeDao()?.deleteAll()
            return null
        }
    }
}