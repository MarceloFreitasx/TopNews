package br.com.topnews.presentations.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.topnews.data.models.ArtsModel
import br.com.topnews.data.repositories.arts.ArtsRepository
import br.com.topnews.data.result.ArtsResult
import br.com.topnews.presentation.HomeViewModel
import br.com.topnews.presentation.arts.ArtsViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ArtsViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsLiveDataObserver: Observer<List<ArtsModel>>

    @Mock
    private lateinit var viewModel: ArtsViewModel

    @Test
    fun `when viewModel getArts get success then sets artsLiveData`() {
        // Arrange
        val newsList = listOf(ArtsModel("123", "Teste", "Teste", "img.png", "teste.com"))
        val resultSuccess = MockRepository(ArtsResult.Success(newsList))
        viewModel = ArtsViewModel(HomeViewModel())
        viewModel.artsLiveData.observeForever(newsLiveDataObserver)

        // Act
        resultSuccess.getArts { result: ArtsResult ->
            when (result) {
                is ArtsResult.Success -> {
                    viewModel.artsLiveData.value = result.news.toMutableList()
                }
            }
        }

        // Assert
        verify(newsLiveDataObserver).onChanged(newsList)
    }

    class MockRepository(private val result: ArtsResult) : ArtsRepository {
        override fun getArts(resultCallback: (result: ArtsResult) -> Unit) {
            resultCallback(result)
        }
    }
}

