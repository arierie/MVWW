package id.arieridwan.mvww.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.core.state.AsyncRequest
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewStateObserver: Observer<AsyncRequest<MovieUiModel>> = mock()

    private val loadMovieUseCase: LoadMoviesUseCase = mock()

    private val listOfUiModel: List<MovieUiModel> = mock()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(loadMovieUseCase)
        viewModel.loadMoviesLiveData.observeForever(viewStateObserver)
    }

    @Test
    fun `load data success`() {
        val params = LoadMoviesUseCase.Params("category", 0)
        whenever(loadMovieUseCase.buildUseCaseObservable(params)).thenReturn(Observable.just(listOfUiModel))
        viewModel.loadMovies(params)
        verify(loadMovieUseCase, times(1)).execute(any(), any(), any(), eq(params))
    }

}