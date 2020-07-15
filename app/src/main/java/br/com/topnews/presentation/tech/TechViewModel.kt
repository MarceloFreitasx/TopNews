package br.com.topnews.presentation.tech

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.topnews.data.models.TechModel
import br.com.topnews.data.repositories.tech.TechApiDataSource
import br.com.topnews.data.repositories.tech.TechRepository
import br.com.topnews.data.result.TechResult
import br.com.topnews.di.components.DaggerTechComponent
import br.com.topnews.presentation.HomeViewModel
import javax.inject.Inject

class TechViewModel(
    private val homeViewModel: HomeViewModel
) : ViewModel() {

    @Inject
    lateinit var dataSource: TechRepository

    init {
        DaggerTechComponent.create().inject(this)
        homeViewModel.viewModelFrag = this
    }

    private var newsList: MutableList<TechModel> = mutableListOf()
    val techLiveData: MutableLiveData<List<TechModel>> = MutableLiveData()

    fun getTech() {
        homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_LOADING
        dataSource.getTech { result: TechResult ->
            when (result) {
                is TechResult.Success -> {
                    newsList = result.news.toMutableList()
                    checkNews()
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_NEWS
                }
                is TechResult.Error -> {
                    homeViewModel.viewFlipperNews.value = homeViewModel.VIEWFLIPPER_ERROR
                }
            }
        }
    }

    fun insertNews(it: TechModel) {
        homeViewModel.techDBRepository.insertNews(it)
        checkNews()
    }

    fun removeNews() {
        val max = 20 + homeViewModel.techDBRepository.getAllNews()!!.size
        homeViewModel.techDBRepository.removeAllNews()
        checkNews(max)
    }

    private fun checkNews(max: Int = 20) {
        val list: MutableList<TechModel> = mutableListOf()
        var i = 0
        for (value in newsList) {
            if (i >= max) break
            if (!homeViewModel.techDBRepository.findNews(value)!!) {
                list.add(value)
                i++
            }
        }
        techLiveData.value = list
    }

    class ViewModelFactory(
        private val homeViewModel: HomeViewModel
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(TechViewModel::class.java)) {
                return TechViewModel(homeViewModel) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}