package br.com.topnews.presentation.news

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import br.com.topnews.R
import br.com.topnews.data.repositories.news.NewsApiDataSource
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val viewModel: NewsViewModel = NewsViewModel.ViewModelFactory(NewsApiDataSource())
            .create(NewsViewModel::class.java)
        viewModel.newsLiveData.observe(this, Observer {
            it?.let { news ->
                with(recyclerNews) {
                    layoutManager =
                        GridLayoutManager(this@NewsActivity, 2, GridLayoutManager.VERTICAL, false)
                    adapter = NewsAdapter(news) {
                        val openURL = Intent(Intent.ACTION_VIEW)
                        openURL.data = Uri.parse(it.url)
                        startActivity(openURL)
                    }
                }
            }
        })
        viewModel.getNews()

        viewModel.viewFlipperNews.observe(this, Observer {
            it?.let { viewFlipper ->
                viewFlipperNews.displayedChild = viewFlipper
            }
        })
    }
}