package br.com.topnews.presentation.arts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.models.ArtsModel
import br.com.topnews.data.repositories.arts.ArtsRepository
import br.com.topnews.data.result.ArtsResult
import br.com.topnews.presentation.HomeViewModel

class ArtsViewModel(
    private val homeViewModel: HomeViewModel,
    private val dataSource: ArtsRepository
) : ViewModel() {

    private var newsList: MutableList<ArtsModel> = mutableListOf()
    val artsLiveData: MutableLiveData<List<ArtsModel>> = MutableLiveData()

    fun getArts() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getArts { result: ArtsResult ->
            when (result) {
                is ArtsResult.Success -> {
                    newsList = result.news.toMutableList()
                    checkNews()
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is ArtsResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
    }

    fun insertNews(it: ArtsModel) {
        homeViewModel.artsDBRepository.insertNews(it)
        checkNews()
    }

    fun removeNews() {
        val max = 20 + homeViewModel.artsDBRepository.getAllNews()!!.size
        homeViewModel.artsDBRepository.removeAllNews()
        checkNews(max)
    }

    private fun checkNews(max: Int = 20) {
        val list: MutableList<ArtsModel> = mutableListOf()
        var i = 0
        for (value in newsList) {
            if (i >= max) break
            if (!homeViewModel.artsDBRepository.findNews(value)!!) {
                list.add(value)
                i++
            }
        }
        artsLiveData.value = list
    }

    class ViewModelFactory(
        private val homeViewModel: HomeViewModel,
        private val dataSource: ArtsRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtsViewModel::class.java)) {
                return ArtsViewModel(homeViewModel, dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}