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

    val healthLiveData: MutableLiveData<List<HealthModel>> = MutableLiveData()

    fun getNews() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getHealth { result: HealthResult ->
            when (result) {
                is HealthResult.Success -> {
                    healthLiveData.value = result.news
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is HealthResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
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