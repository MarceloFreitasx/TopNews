package br.com.topnews.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.topnews.R

class HomeViewModel(supportFragmentManager: FragmentManager) : ViewModel() {

    val fr = supportFragmentManager
    val viewFlipperNews: MutableLiveData<Int> = MutableLiveData()

    val VIEWFLIPPER_LOADING = 0
    val VIEWFLIPPER_NEWS = 1
    val VIEWFLIPPER_ERROR = 2

    fun replaceFragment(fragment: Fragment) {
        val transaction = fr.beginTransaction()
        transaction.replace(R.id.fragmentView, fragment)
        transaction.commit()
    }
}