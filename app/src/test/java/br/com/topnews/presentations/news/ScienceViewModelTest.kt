package br.com.topnews.presentations.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.topnews.data.models.ScienceModel
import br.com.topnews.data.repositories.science.ScienceRepository
import br.com.topnews.data.result.ScienceResult
import br.com.topnews.presentation.HomeViewModel
import br.com.topnews.presentation.science.ScienceViewModel
import com.nhaarman.mockitokotlin2.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ScienceViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var newsLiveDataObserver: Observer<List<ScienceModel>>

    @Mock
    private lateinit var viewModel: ScienceViewModel

    @Test
    fun `when viewModel getScience get success then sets scienceLiveData`() {
        // Arrange
        val newsList = listOf(ScienceModel("123", "Teste", "Teste", "img.png", "teste.com"))
        val resultSuccess = MockRepository(ScienceResult.Success(newsList))
        viewModel = ScienceViewModel(HomeViewModel())
        viewModel.scienceLiveData.observeForever(newsLiveDataObserver)

        // Act
        resultSuccess.getScience { result: ScienceResult ->
            when (result) {
                is ScienceResult.Success -> {
                    viewModel.scienceLiveData.value = result.news.toMutableList()
                }
            }
        }

        // Assert
        verify(newsLiveDataObserver).onChanged(newsList)
    }

    class MockRepository(private val result: ScienceResult) : ScienceRepository {
        override fun getScience(resultCallback: (result: ScienceResult) -> Unit) {
            resultCallback(result)
        }
    }
}

