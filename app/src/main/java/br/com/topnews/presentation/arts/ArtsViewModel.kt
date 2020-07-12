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

    val artsLiveData: MutableLiveData<List<ArtsModel>> = MutableLiveData()

    fun getArts() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getArts { result: ArtsResult ->
            when (result) {
                is ArtsResult.Success -> {
                    artsLiveData.value = result.news
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is ArtsResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
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