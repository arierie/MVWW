package id.arieridwan.mvww.domain.usecase

import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.presentation.model.MovieUiModel
import id.arieridwan.mvww.test.Constants
import io.reactivex.Single
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
        val params = LoadMoviesUseCase.Params(Constants.MOCK_CATEGORY, Constants.MOCK_PAGE)
        whenever(movieRepository.loadMoviesFromNetwork(any(), any())).thenReturn(Single.just(listOfUiModel))

        loadMovieUseCase.buildUseCaseSingle(params)

        verify(movieRepository, times(1)).loadMoviesFromNetwork(Constants.MOCK_CATEGORY, Constants.MOCK_PAGE)
        verifyNoMoreInteractions(movieRepository)
    }

}