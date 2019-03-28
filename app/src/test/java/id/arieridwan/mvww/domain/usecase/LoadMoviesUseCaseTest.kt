package id.arieridwan.mvww.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import id.arieridwan.mvww.data.disk.entity.Movie
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.presentation.entity.MovieUiModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LoadMoviesUseCaseTest {

    private val movieRepository = mock<MoviesRepository>()
    private lateinit var loadMovieUseCase: LoadMoviesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        loadMovieUseCase = LoadMoviesUseCase(movieRepository)
    }

    @Test
    fun shouldCallRepository() {
        val category = ""
        val page = 0
        val movie = MovieUiModel("", "", "", "", "", 0, false,0.0, 0.0)
        val movies = mutableListOf<MovieUiModel>()
        movies.add(movie)
        val params = LoadMoviesUseCase.Params("", 0)
        loadMovieUseCase.buildUseCaseObservable(params)
        verify(movieRepository, times(1)).loadMoviesFromNetwork(category, page)
        verifyNoMoreInteractions(movieRepository)
    }

}