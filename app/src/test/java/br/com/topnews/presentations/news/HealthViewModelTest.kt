package br.com.topnews.presentations.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.topnews.data.models.HealthModel
import br.com.topnews.data.repositories.health.HealthRepository
import br.com.topnews.data.result.HealthResult
import br.com.topnews.presentation.HomeViewModel
import br.com.topnews.presentation.health.HealthViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HealthViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsLiveDataObserver: Observer<List<HealthModel>>

    @Mock
    private lateinit var viewModel: HealthViewModel

    @Test
    fun `when viewModel getHealth get success then sets healthLiveData`() {
        // Arrange
        val newsList = listOf(HealthModel("123", "Teste", "Teste", "img.png", "teste.com"))
        val resultSuccess = MockRepository(HealthResult.Success(newsList))
        viewModel = HealthViewModel(HomeViewModel())
        viewModel.healthLiveData.observeForever(newsLiveDataObserver)

        // Act
        resultSuccess.getHealth { result: HealthResult ->
            when (result) {
                is HealthResult.Success -> {
                    viewModel.healthLiveData.value = result.news.toMutableList()
                }
            }
        }

        // Assert
        verify(newsLiveDataObserver).onChanged(newsList)
    }

    class MockRepository(private val result: HealthResult) : HealthRepository {
        override fun getHealth(resultCallback: (result: HealthResult) -> Unit) {
            resultCallback(result)
        }
    }
}

