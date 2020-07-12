package br.com.topnews.presentation.tech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.models.TechModel
import br.com.topnews.data.repositories.tech.TechRepository
import br.com.topnews.data.result.TechResult
import br.com.topnews.presentation.HomeViewModel

class TechViewModel(
    private val homeViewModel: HomeViewModel,
    private val dataSource: TechRepository
) : ViewModel() {

    val techLiveData: MutableLiveData<List<TechModel>> = MutableLiveData()

    fun getTech() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getTech { result: TechResult ->
            when (result) {
                is TechResult.Success -> {
                    techLiveData.value = result.news
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is TechResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
    }

    class ViewModelFactory(
        private val homeViewModel: HomeViewModel,
        private val dataSource: TechRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TechViewModel::class.java)) {
                return TechViewModel(homeViewModel, dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}