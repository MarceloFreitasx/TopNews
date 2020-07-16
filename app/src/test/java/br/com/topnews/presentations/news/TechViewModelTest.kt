package br.com.topnews.presentations.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.topnews.data.models.TechModel
import br.com.topnews.data.repositories.tech.TechRepository
import br.com.topnews.data.result.TechResult
import br.com.topnews.presentation.HomeViewModel
import br.com.topnews.presentation.tech.TechViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TechViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var techLiveDataObserver: Observer<List<TechModel>>

    @Mock
    private lateinit var viewModel: TechViewModel

    @Test
    fun `when viewModel getTech get success then sets techLiveData`() {
        // Arrange
        val newsList = listOf(TechModel("123", "Teste", "Teste", "img.png", "teste.com"))
        val resultSuccess = MockRepository(TechResult.Success(newsList))
        viewModel = TechViewModel(HomeViewModel())
        viewModel.techLiveData.observeForever(techLiveDataObserver)

        // Act
        resultSuccess.getTech { result: TechResult ->
            when (result) {
                is TechResult.Success -> {
                    viewModel.techLiveData.value = result.news.toMutableList()
                }
            }
        }

        // Assert
        verify(techLiveDataObserver).onChanged(newsList)
    }

    class MockRepository(private val result: TechResult) : TechRepository {
        override fun getTech(resultCallback: (result: TechResult) -> Unit) {
            resultCallback(result)
        }
    }
}

