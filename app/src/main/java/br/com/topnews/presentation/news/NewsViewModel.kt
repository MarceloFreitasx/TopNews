package br.com.topnews.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.local.db.home.HomeRepository
import br.com.topnews.data.models.NewsModel
import br.com.topnews.data.repositories.news.NewsRepository
import br.com.topnews.data.result.NewsResult
import br.com.topnews.presentation.HomeViewModel

class NewsViewModel(
    private val homeViewModel: HomeViewModel?,
    private val dataSource: NewsRepository
) : ViewModel() {

    val newsLiveData: MutableLiveData<List<NewsModel>> = MutableLiveData()
    val homeRepository = HomeRepository()

    fun getNews() {
        homeViewModel?.viewFlipperNews?.value = homeViewModel?.VIEWFLIPPER_LOADING
        dataSource.getNews { result: NewsResult ->
            when (result) {
                is NewsResult.Success -> {
                    newsLiveData.value = result.news
                    homeViewModel?.viewFlipperNews?.value = homeViewModel?.VIEWFLIPPER_NEWS
                    checkNews()
                }
                is NewsResult.Error -> {
                    homeViewModel?.viewFlipperNews?.value = homeViewModel?.VIEWFLIPPER_ERROR
                }
            }
        }
    }

    fun checkNews() {
        val list: MutableList<NewsModel> = mutableListOf()
        for (value in newsLiveData.value!!)
            if (!homeRepository.findNews(value)!!)
                list.add(value)
        newsLiveData.value = list
    }

    class ViewModelFactory(
        private val homeViewModel: HomeViewModel,
        private val dataSource: NewsRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
                return NewsViewModel(homeViewModel, dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }


}