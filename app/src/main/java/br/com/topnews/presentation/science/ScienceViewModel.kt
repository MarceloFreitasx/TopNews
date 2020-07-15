package br.com.topnews.presentation.science

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.models.ScienceModel
import br.com.topnews.data.repositories.science.ScienceApiDataSource
import br.com.topnews.data.repositories.science.ScienceRepository
import br.com.topnews.data.result.ScienceResult
import br.com.topnews.di.components.DaggerScienceComponent
import br.com.topnews.presentation.HomeViewModel
import javax.inject.Inject

class ScienceViewModel(
    private val homeViewModel: HomeViewModel
) : ViewModel() {

    @Inject
    lateinit var dataSource: ScienceRepository

    init {
        DaggerScienceComponent.create().inject(this)
        homeViewModel.viewModelFrag = this
    }

    private var newsList: MutableList<ScienceModel> = mutableListOf()
    val scienceLiveData: MutableLiveData<List<ScienceModel>> = MutableLiveData()

    fun getScience() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getScience { result: ScienceResult ->
            when (result) {
                is ScienceResult.Success -> {
                    newsList = result.news.toMutableList()
                    checkNews()
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is ScienceResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
    }

    fun insertNews(it: ScienceModel) {
        homeViewModel.scienceDBRepository.insertNews(it)
        checkNews()
    }

    fun removeNews() {
        val max = 20 + homeViewModel.scienceDBRepository.getAllNews()!!.size
        homeViewModel.scienceDBRepository.removeAllNews()
        checkNews(max)
    }

    private fun checkNews(max: Int = 20) {
        val list: MutableList<ScienceModel> = mutableListOf()
        var i = 0
        for (value in newsList) {
            if (i >= max) break
            if (!homeViewModel.scienceDBRepository.findNews(value)!!) {
                list.add(value)
                i++
            }
        }
        scienceLiveData.value = list
    }

    class ViewModelFactory(
        private val homeViewModel: HomeViewModel
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ScienceViewModel::class.java)) {
                return ScienceViewModel(homeViewModel) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}