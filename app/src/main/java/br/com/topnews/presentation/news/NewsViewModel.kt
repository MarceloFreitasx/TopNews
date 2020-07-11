package br.com.topnews.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.models.NewsModel
import br.com.topnews.data.repositories.news.NewsRepository
import br.com.topnews.data.result.NewsResult

class NewsViewModel(private val dataSource: NewsRepository) : ViewModel() {

    val newsLiveData: MutableLiveData<List<NewsModel>> = MutableLiveData()
    val viewFlipperNews: MutableLiveData<Int> = MutableLiveData()

    fun getNews() {
        viewFlipperNews.value = VIEWFLIPPER_LOADING
        dataSource.getNews { result: NewsResult ->
            when (result) {
                is NewsResult.Success -> {
                    newsLiveData.value = result.news
                    viewFlipperNews.value = VIEWFLIPPER_NEWS
                }
                is NewsResult.Error -> {
                    viewFlipperNews.value = VIEWFLIPPER_ERROR
                }
            }
        }
    }

    class ViewModelFactory(private val dataSource: NewsRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                return NewsViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    companion object {
        private const val VIEWFLIPPER_LOADING = 0
        private const val VIEWFLIPPER_NEWS = 1
        private const val VIEWFLIPPER_ERROR = 2
    }
}