package id.arieridwan.mvww.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.core.custom.schedulers.BaseSchedulerProvider
import id.arieridwan.mvww.core.state.Async
import id.arieridwan.mvww.test.TestSchedulerProvider
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import junit.framework.Assert.assertTrue
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
    private val loadMovieUseCase: LoadMoviesUseCase = mock()
    private val listOfUiModel: List<MovieUiModel> = mock()

    private lateinit var viewModel: HomeViewModel
    private lateinit var testScheduler: TestScheduler
    private lateinit var schedulerProvider: BaseSchedulerProvider
    private lateinit var params: LoadMoviesUseCase.Params

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testScheduler = TestScheduler()
        schedulerProvider = TestSchedulerProvider(testScheduler)
        viewModel = HomeViewModel(loadMovieUseCase, schedulerProvider)
    }

    @Test
    fun `load data success`() {
        params = LoadMoviesUseCase.Params("category", 0)
        whenever(loadMovieUseCase.loadMovies(params)).thenReturn(Observable.just(listOfUiModel))

        viewModel.loadMoviesLiveData.observeForever(viewStateObserver)
        viewModel.loadMovies(params)
        testScheduler.triggerActions()

        verify(loadMovieUseCase, times(1)).loadMovies(params)

        argumentCaptor<Async<List<MovieUiModel>>> {
            verify(viewStateObserver, times(2)).onChanged(capture())
            val (firstState, secondState) = allValues
            assertTrue(firstState is Async.Loading)
            assertTrue(secondState is Async.Success)
        }
    }

}