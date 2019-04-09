package id.arieridwan.mvww.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.core.state.Async
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.presentation.model.MovieUiModel
import id.arieridwan.mvww.test.BaseTest
import id.arieridwan.mvww.test.RxImmediateSchedulerRule
import io.reactivex.Observable
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class HomeViewModelTest: BaseTest() {

    @Rule
    @JvmField
    var rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val viewStateObserver: Observer<Async<List<MovieUiModel>>> = mock()
    private val loadMovieUseCase: LoadMoviesUseCase = mock()
    private val listOfUiModel: List<MovieUiModel> = mock()

    private lateinit var viewModel: HomeViewModel
    private lateinit var params: LoadMoviesUseCase.Params

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        viewModel = HomeViewModel(loadMovieUseCase)
    }

    @Test
    fun `load data success`() {
        params = LoadMoviesUseCase.Params("category", 0)
        whenever(loadMovieUseCase.buildUseCaseObservable(params)).thenReturn(Observable.just(listOfUiModel))

        viewModel.loadMoviesLiveData.observeForever(viewStateObserver)
        viewModel.loadMovies(params)

        verify(loadMovieUseCase, times(1)).buildUseCaseObservable(any())

        argumentCaptor<Async<List<MovieUiModel>>> {
            verify(viewStateObserver, times(3)).onChanged(capture())
            val (firstState, secondState, thirdState) = allValues
            assertTrue(firstState is Async.Loading)
            assertTrue(secondState is Async.Success)
            assertTrue(thirdState is Async.Complete)
        }
    }

}