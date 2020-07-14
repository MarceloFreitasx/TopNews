package br.com.topnews.presentation.health

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.models.HealthModel
import br.com.topnews.data.repositories.health.HealthRepository
import br.com.topnews.data.result.HealthResult
import br.com.topnews.presentation.HomeViewModel

class HealthViewModel(
    private val homeViewModel: HomeViewModel,
    private val dataSource: HealthRepository
) : ViewModel() {

    private var newsList: MutableList<HealthModel> = mutableListOf()
    val healthLiveData: MutableLiveData<List<HealthModel>> = MutableLiveData()

    fun getNews() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getHealth { result: HealthResult ->
            when (result) {
                is HealthResult.Success -> {
                    newsList = result.news.toMutableList()
                    checkNews()
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is HealthResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
    }

    fun insertNews(it: HealthModel) {
        homeViewModel.healthDBRepository.insertNews(it)
        checkNews()
    }

    fun removeNews() {
        val max = 20 + homeViewModel.healthDBRepository.getAllNews()!!.size
        homeViewModel.healthDBRepository.removeAllNews()
        checkNews(max)
    }

    private fun checkNews(max: Int = 20) {
        val list: MutableList<HealthModel> = mutableListOf()
        var i = 0
        for (value in newsList) {
            if (i >= max) break
            if (!homeViewModel.healthDBRepository.findNews(value)!!) {
                list.add(value)
                i++
            }
        }
        healthLiveData.value = list
    }

    class ViewModelFactory(
        private val homeViewModel: HomeViewModel,
        private val dataSource: HealthRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HealthViewModel::class.java)) {
                return HealthViewModel(homeViewModel, dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}