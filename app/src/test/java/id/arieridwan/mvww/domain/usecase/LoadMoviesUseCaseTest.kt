package id.arieridwan.mvww.domain.usecase

import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LoadMoviesUseCaseTest {

    private val movieRepository: MoviesRepository = mock()
    private val listOfUiModel: List<MovieUiModel> = mock()

    private lateinit var loadMovieUseCase: LoadMoviesUseCase

    @Before
    fun setUp() {
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