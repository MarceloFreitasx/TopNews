package br.com.topnews.presentation.science

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.models.ScienceModel
import br.com.topnews.data.repositories.science.ScienceRepository
import br.com.topnews.data.result.ScienceResult
import br.com.topnews.presentation.HomeViewModel

class ScienceViewModel(
    private val homeViewModel: HomeViewModel,
    private val dataSource: ScienceRepository
) : ViewModel() {

    val scienceLiveData: MutableLiveData<List<ScienceModel>> = MutableLiveData()

    fun getScience() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getScience { result: ScienceResult ->
            when (result) {
                is ScienceResult.Success -> {
                    scienceLiveData.value = result.news
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is ScienceResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
    }

    class ViewModelFactory(
        private val homeViewModel: HomeViewModel,
        private val dataSource: ScienceRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ScienceViewModel::class.java)) {
                return ScienceViewModel(homeViewModel, dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}