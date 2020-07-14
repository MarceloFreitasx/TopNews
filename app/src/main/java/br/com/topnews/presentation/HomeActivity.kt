package br.com.topnews.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.topnews.R
import br.com.topnews.presentation.arts.ArtsFragment
import br.com.topnews.presentation.health.HealthFragment
import br.com.topnews.presentation.news.NewsFragment
import br.com.topnews.presentation.science.ScienceFragment
import br.com.topnews.presentation.tech.TechFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    val viewModel = HomeViewModel(supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""

        viewModel.viewFlipperNews.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperNews.displayedChild = viewFlipper
            }
        })

        viewModel.replaceFragment(NewsFragment(viewModel))
        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    viewModel.selectedTab = tab.position
                    when (tab.position) {
                        // Home Tab
                        0 -> viewModel.replaceFragment(NewsFragment(viewModel))
                        // Arts Tab
                        1 -> viewModel.replaceFragment(ArtsFragment(viewModel))
                        // Science Tab
                        2 -> viewModel.replaceFragment(ScienceFragment(viewModel))
                        // Health Tab
                        3 -> viewModel.replaceFragment(HealthFragment(viewModel))
                        //Tech Tab
                        4 -> viewModel.replaceFragment(TechFragment(viewModel))
                    }
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_atualizar -> {
            viewModel.atualizarNews()
            true
        }

        R.id.action_desmarcar -> {
            viewModel.desmarcarNews()
            true
        }

        else -> {
            super.onOptionsItemSelected(item)
        }
    }

}