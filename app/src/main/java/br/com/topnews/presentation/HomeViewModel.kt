package br.com.topnews.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.topnews.R
import br.com.topnews.data.local.db.arts.ArtsDBRepository
import br.com.topnews.data.local.db.health.HealthDBRepository
import br.com.topnews.data.local.db.home.HomeDBRepository
import br.com.topnews.data.local.db.science.ScienceDBRepository
import br.com.topnews.data.local.db.tech.TechDBRepository
import br.com.topnews.presentation.arts.ArtsFragment
import br.com.topnews.presentation.arts.ArtsViewModel
import br.com.topnews.presentation.health.HealthFragment
import br.com.topnews.presentation.health.HealthViewModel
import br.com.topnews.presentation.news.NewsFragment
import br.com.topnews.presentation.news.NewsViewModel
import br.com.topnews.presentation.science.ScienceFragment
import br.com.topnews.presentation.science.ScienceViewModel
import br.com.topnews.presentation.tech.TechFragment
import br.com.topnews.presentation.tech.TechViewModel

class HomeViewModel : ViewModel() {

    var fr: FragmentManager? = null
    lateinit var fragment: Fragment
    lateinit var viewModelFrag: ViewModel
    val homeDBRepository = HomeDBRepository()
    val artsDBRepository = ArtsDBRepository()
    val healthDBRepository = HealthDBRepository()
    val scienceDBRepository = ScienceDBRepository()
    val techDBRepository = TechDBRepository()

    val viewFlipperNews: MutableLiveData<Int> = MutableLiveData()
    var selectedTab: Int = 0

    val VIEWFLIPPER_LOADING = 0
    val VIEWFLIPPER_NEWS = 1
    val VIEWFLIPPER_ERROR = 2

    fun replaceFragment(frag: Fragment) {
        when {
            fr != null -> {
                val transaction = fr?.beginTransaction()
                transaction?.replace(R.id.fragmentView, frag)
                transaction?.commit()
                fragment = frag
            }
        }
    }

    fun desmarcarNews() {
        when (fragment.javaClass) {
            // Home Tab
            NewsFragment::class.java -> (viewModelFrag as NewsViewModel).removeNews()
            // Arts Tab
            ArtsFragment::class.java -> (viewModelFrag as ArtsViewModel).removeNews()
            // Science Tab
            ScienceFragment::class.java -> (viewModelFrag as ScienceViewModel).removeNews()
            // Health Tab
            HealthFragment::class.java -> (viewModelFrag as HealthViewModel).removeNews()
            //Tech Tab
            TechFragment::class.java -> (viewModelFrag as TechViewModel).removeNews()
        }
    }

    fun atualizarNews() {
        when (fragment.javaClass) {
            // Home Tab
            NewsFragment::class.java -> (viewModelFrag as NewsViewModel).getNews()
            // Arts Tab
            ArtsFragment::class.java -> (viewModelFrag as ArtsViewModel).getArts()
            // Science Tab
            ScienceFragment::class.java -> (viewModelFrag as ScienceViewModel).getScience()
            // Health Tab
            HealthFragment::class.java -> (viewModelFrag as HealthViewModel).getNews()
            //Tech Tab
            TechFragment::class.java -> (viewModelFrag as TechViewModel).getTech()
        }
    }

}