package id.arieridwan.mvww.domain.usecase

import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.core.custom.schedulers.BaseSchedulerProvider
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.presentation.model.MovieUiModel
import id.arieridwan.mvww.test.BaseTest
import id.arieridwan.mvww.test.TestSchedulerProvider
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LoadMoviesUseCaseTest: BaseTest() {

    private val movieRepository: MoviesRepository = mock()
    private val listOfUiModel: List<MovieUiModel> = mock()

    private lateinit var loadMovieUseCase: LoadMoviesUseCase

    @Before
    override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        loadMovieUseCase = LoadMoviesUseCase(movieRepository)
    }

    @Test
    fun shouldCallRepository() {
        val params = LoadMoviesUseCase.Params("category", 0)
        whenever(movieRepository.loadMoviesFromNetwork(any(), any())).thenReturn(Observable.just(listOfUiModel))
        loadMovieUseCase.buildUseCaseObservable(params)
        verify(movieRepository, times(1)).loadMoviesFromNetwork("category", 0)
        verifyNoMoreInteractions(movieRepository)
    }

}