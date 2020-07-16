package br.com.topnews.presentations.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.topnews.data.models.NewsModel
import br.com.topnews.data.repositories.news.NewsRepository
import br.com.topnews.data.result.NewsResult
import br.com.topnews.presentation.HomeViewModel
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
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsLiveDataObserver: Observer<List<NewsModel>>

    @Mock
    private lateinit var viewModel: NewsViewModel

    @Test
    fun `when viewModel getNews get success then sets newsLiveData`() {
        // Arrange
        val newsList = listOf(NewsModel("123", "Teste", "Teste", "img.png", "teste.com"))
        val resultSuccess = MockRepository(NewsResult.Success(newsList))
        viewModel = NewsViewModel(HomeViewModel())
        viewModel.newsLiveData.observeForever(newsLiveDataObserver)

        // Act
        resultSuccess.getNews { result: NewsResult ->
            when (result) {
                is NewsResult.Success -> {
                    viewModel.newsLiveData.value = result.news.toMutableList()
                }
            }
        }

        // Assert
        verify(newsLiveDataObserver).onChanged(newsList)
    }

    class MockRepository(private val result: NewsResult) : NewsRepository {
        override fun getNews(resultCallback: (result: NewsResult) -> Unit) {
            resultCallback(result)
        }
    }
}

