package id.arieridwan.mvww.data.repositoryimpl

import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.data.disk.dao.MovieDao
import id.arieridwan.mvww.data.disk.entity.Movie
import id.arieridwan.mvww.data.network.response.MovieListResponse
import id.arieridwan.mvww.data.network.service.ApiService
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.test.Constants
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MoviesRepositoryImplTest {

    private val apiService = mock<ApiService>()
    private val movieDao = mock<MovieDao>()
    private val moviesResponse: MovieListResponse = mock()
    private val movies: List<Movie> = mock()

    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        moviesRepository = MoviesRepositoryImpl(Constants.MOCK_API_KEY, apiService, movieDao)
    }

    @Test
    fun loadMoviesFromNetworkSuccess() {
        whenever(apiService.getMovies(Constants.MOCK_CATEGORY, Constants.MOCK_API_KEY, Constants.MOCK_PAGE)).thenReturn(Observable.just(moviesResponse))

        moviesRepository.loadMoviesFromNetwork(Constants.MOCK_CATEGORY, Constants.MOCK_PAGE)

        verify(apiService, times(1)).getMovies(Constants.MOCK_CATEGORY, Constants.MOCK_API_KEY, Constants.MOCK_PAGE)
        verifyNoMoreInteractions(apiService)
    }

    @Test
    fun loadMoviesFromDiskSuccess() {
        whenever(movieDao.getMovies()).thenReturn(Single.just(movies))

        moviesRepository.loadMoviesFromDisk(Constants.MOCK_CATEGORY, Constants.MOCK_PAGE)

        verify(movieDao, times(1)).getMovies()
        verifyNoMoreInteractions(movieDao)
    }

}