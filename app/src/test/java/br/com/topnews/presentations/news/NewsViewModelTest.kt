package br.com.topnews.presentations.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.topnews.data.models.NewsModel
import br.com.topnews.data.repositories.news.NewsRepository
import br.com.topnews.data.result.NewsResult
import br.com.topnews.presentation.news.NewsViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsLiveDataObserver: Observer<List<NewsModel>>

    @Mock
    private lateinit var viewFlipperNewsObserver: Observer<Int>

    private lateinit var viewModel: NewsViewModel

    @Test
    fun `when viewmodel getNews get success then sets newsLiveData`() {
        // Arrange
        val news = listOf(
            NewsModel("Titulo 1", "Autor 1", "Imagem 1", "Url")
        )
        val resultSuccess = MockRepository(NewsResult.Success(news))
        viewModel = NewsViewModel(null, resultSuccess)
        viewModel.newsLiveData.observeForever(newsLiveDataObserver)

        // Act
        viewModel.getNews()

        // Assert
        verify(newsLiveDataObserver).onChanged(news)
        verify(viewFlipperNewsObserver).onChanged(0)
    }

    @Test
    fun `when viewmodel getNews get error then sets viewFlipperNews`() {
        //Arrange
        val resultError = MockRepository(NewsResult.Error())
        viewModel = NewsViewModel(null, resultError)

        // Act
        viewModel.getNews()

        // Assert
        verify(viewFlipperNewsObserver).onChanged(2)
    }
}

class MockRepository(private val result: NewsResult) : NewsRepository {
    override fun getNews(resultCallback: (result: NewsResult) -> Unit) {
        resultCallback(result)
    }
}