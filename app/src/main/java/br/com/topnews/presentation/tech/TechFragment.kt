package br.com.topnews.presentation.tech

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.topnews.R
import br.com.topnews.data.models.TechModel
import br.com.topnews.presentation.HomeViewModel
import kotlinx.android.synthetic.main.activity_news.*

class TechFragment(private val homeViewModel: HomeViewModel) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.activity_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: TechViewModel =
            TechViewModel.ViewModelFactory(homeViewModel)
                .create(TechViewModel::class.java)

        activity?.let {
            viewModel.techLiveData.observe(it, Observer {
                it?.let { news ->
                    with(recyclerNews) {
                        layoutManager =
                            GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
                        adapter = TechAdapter(news) { it: TechModel, s: String ->
                            if (s == "lido") {
                                viewModel.insertNews(it)
                                Toast.makeText(
                                    context,
                                    "Notícia marcada como lida",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                val openURL = Intent(Intent.ACTION_VIEW)
                                openURL.data = Uri.parse(it.url)
                                startActivity(openURL)
                            }
                        }
                    }
                }
            })
            viewModel.getTech()
        }
    }
}