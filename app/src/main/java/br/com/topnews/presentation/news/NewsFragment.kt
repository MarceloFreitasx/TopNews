package br.com.topnews.presentation.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.topnews.R
import br.com.topnews.data.repositories.news.NewsApiDataSource
import br.com.topnews.presentation.HomeViewModel
import kotlinx.android.synthetic.main.activity_news.*

class NewsFragment(private val homeViewModel: HomeViewModel) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.activity_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val viewModel: NewsViewModel =
            NewsViewModel.ViewModelFactory(homeViewModel, NewsApiDataSource())
                .create(NewsViewModel::class.java)

        activity?.let {
            viewModel.newsLiveData.observe(it, Observer {
                it?.let { news ->
                    with(recyclerNews) {
                        layoutManager =
                            GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
                        adapter = NewsAdapter(news) {
                            viewModel.homeRepository.insertNews(it)
                            viewModel.checkNews()
                            val openURL = Intent(Intent.ACTION_VIEW)
                            openURL.data = Uri.parse(it.url)
                            startActivity(openURL)
                        }
                    }
                }
            })
            viewModel.getNews()
        }
    }
}