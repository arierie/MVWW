package id.arieridwan.mvww.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.core.reactivex.schedulers.BaseSchedulerProvider
import id.arieridwan.mvww.core.state.Async
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.presentation.model.MovieUiModel
import id.arieridwan.mvww.test.Constants
import id.arieridwan.mvww.test.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.observers.DisposableSingleObserver
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewStateObserver: Observer<Async<List<MovieUiModel>>> = mock()
    private val listOfUiModel: List<MovieUiModel> = mock()
    private val loadMovieUseCase: LoadMoviesUseCase = mock()

    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var observerArgumentCaptor: KArgumentCaptor<DisposableSingleObserver<List<MovieUiModel>>>
    private lateinit var viewModel: HomeViewModel
    private lateinit var params: LoadMoviesUseCase.Params

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        schedulerProvider = TestSchedulerProvider()
        observerArgumentCaptor = argumentCaptor()
        viewModel = HomeViewModel(loadMovieUseCase, schedulerProvider)
    }

    @Test
    fun `load movies success`() {
        params = LoadMoviesUseCase.Params(Constants.MOCK_CATEGORY, Constants.MOCK_PAGE)
        whenever(loadMovieUseCase.buildUseCaseSingle(params)).thenReturn(Single.just(listOfUiModel))

        viewModel.loadMoviesLiveData.observeForever(viewStateObserver)
        viewModel.loadMovies(params)

        verify(loadMovieUseCase).execute(observerArgumentCaptor.capture(), eq(params), eq(schedulerProvider))
        observerArgumentCaptor.firstValue.onSuccess(listOfUiModel)

        argumentCaptor<Async<List<MovieUiModel>>> {
            verify(viewStateObserver, times(2)).onChanged(capture())
            val (firstState, secondState) = allValues
            assertTrue(firstState is Async.Loading)
            assertTrue(secondState is Async.Success)
        }
        verifyNoMoreInteractions(loadMovieUseCase)
    }

    @Test
    fun `load movies fail`() {
        val expectedError = Throwable(Constants.MOCK_ERROR)
        params = LoadMoviesUseCase.Params(Constants.MOCK_CATEGORY, Constants.MOCK_PAGE)
        whenever(loadMovieUseCase.buildUseCaseSingle(params)).thenReturn(Single.error(expectedError))

        viewModel.loadMoviesLiveData.observeForever(viewStateObserver)
        viewModel.loadMovies(params)
        verify(loadMovieUseCase).execute(observerArgumentCaptor.capture(), eq(params), eq(schedulerProvider))
        observerArgumentCaptor.firstValue.onError(expectedError)

        argumentCaptor<Async<List<MovieUiModel>>> {
            verify(viewStateObserver, times(2)).onChanged(capture())
            val (firstState, secondState) = allValues
            assertTrue(firstState is Async.Loading)
            assertTrue(secondState is Async.Fail)
        }
        verifyNoMoreInteractions(loadMovieUseCase)
    }

}