package br.com.topnews.presentation.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.models.NewsModel
import br.com.topnews.data.repositories.news.NewsRepository
import br.com.topnews.data.result.NewsResult
import br.com.topnews.presentation.HomeViewModel

class NewsViewModel(
    private val homeViewModel: HomeViewModel,
    private val dataSource: NewsRepository
) : ViewModel() {

    private var newsList: MutableList<NewsModel> = mutableListOf()
    val newsLiveData: MutableLiveData<List<NewsModel>> = MutableLiveData()

    fun getNews() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getNews { result: NewsResult ->
            when (result) {
                is NewsResult.Success -> {
                    newsList = result.news.toMutableList()
                    checkNews()
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is NewsResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
    }

    fun insertNews(it: NewsModel) {
        homeViewModel.homeDBRepository.insertNews(it)
        checkNews()
    }

    fun removeNews() {
        val max = 20 + homeViewModel.homeDBRepository.getAllNews()!!.size
        homeViewModel.homeDBRepository.removeAllNews()
        checkNews(max)
    }

    private fun checkNews(max: Int = 20) {
        val list: MutableList<NewsModel> = mutableListOf()
        var i = 0
        for (value in newsList) {
            if (i >= max) break
            if (!homeViewModel.homeDBRepository.findNews(value)!!) {
                list.add(value)
                i++
            }
        }
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