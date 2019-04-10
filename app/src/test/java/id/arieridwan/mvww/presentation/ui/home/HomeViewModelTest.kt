package id.arieridwan.mvww.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.core.custom.schedulers.BaseSchedulerProvider
import id.arieridwan.mvww.core.state.Async
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.presentation.model.MovieUiModel
import id.arieridwan.mvww.test.Constants
import id.arieridwan.mvww.test.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewStateObserver: Observer<Async<List<MovieUiModel>>> = mock()
    private val listOfUiModel: List<MovieUiModel> = mock()
    private val schedulerProvider: BaseSchedulerProvider = TestSchedulerProvider()
    private val loadMovieUseCase: LoadMoviesUseCase = mock()

    private lateinit var observerArgumentCaptor: KArgumentCaptor<DisposableObserver<List<MovieUiModel>>>
    private lateinit var viewModel: HomeViewModel
    private lateinit var params: LoadMoviesUseCase.Params

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        observerArgumentCaptor = argumentCaptor()
        viewModel = HomeViewModel(loadMovieUseCase, schedulerProvider)
    }

    @Test
    fun `load movies success`() {
        params = LoadMoviesUseCase.Params(Constants.DEFAULT_CATEGORY, Constants.DEFAULT_PAGE)
        whenever(loadMovieUseCase.buildUseCaseObservable(params)).thenReturn(Observable.just(listOfUiModel))

        viewModel.loadMoviesLiveData.observeForever(viewStateObserver)
        viewModel.loadMovies(params)
        verify(loadMovieUseCase).execute(observerArgumentCaptor.capture(), eq(params), eq(schedulerProvider))

        observerArgumentCaptor.firstValue.onNext(listOfUiModel)
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
        val expectedError = Throwable(Constants.DEFAULT_ERROR)
        params = LoadMoviesUseCase.Params(Constants.DEFAULT_CATEGORY, Constants.DEFAULT_PAGE)
        whenever(loadMovieUseCase.buildUseCaseObservable(params)).thenReturn(Observable.error(expectedError))

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