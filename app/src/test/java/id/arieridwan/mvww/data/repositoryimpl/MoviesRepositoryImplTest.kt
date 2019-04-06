package id.arieridwan.mvww.data.repositoryimpl

import com.nhaarman.mockitokotlin2.*
import id.arieridwan.mvww.data.disk.dao.MovieDao
import id.arieridwan.mvww.data.disk.entity.Movie
import id.arieridwan.mvww.data.network.response.MovieListResponse
import id.arieridwan.mvww.data.network.response.MovieResponse
import id.arieridwan.mvww.data.network.service.ApiService
import id.arieridwan.mvww.domain.repository.MoviesRepository
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MoviesRepositoryImplTest {

    private val apiKey = "myAwesomeButRandomKey"
    private val apiService = mock<ApiService>()
    private val movieDao = mock<MovieDao>()
    private lateinit var moviesRepository: MoviesRepository

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        moviesRepository = MoviesRepositoryImpl(apiKey, apiService, movieDao)
    }

    @Test
    fun loadMoviesFromNetworkSuccess() {
        val category = ""
        val page = 0
        val movieResponse = MovieResponse("", "", "", "", "", 0, false,0.0, 0.0)
        val movies = mutableListOf<MovieResponse>()
        movies.add(movieResponse)
        val moviesResponse = MovieListResponse(0, 0, 0, movies)
        whenever(apiService.getMovies(category, apiKey, page)).thenReturn(Observable.just(moviesResponse))
        moviesRepository.loadMoviesFromNetwork(category, page)
        verify(apiService, times(1)).getMovies(category, apiKey, page)
        verifyNoMoreInteractions(apiService)
    }

    @Test
    fun loadMoviesFromDiskSuccess() {
        val category = ""
        val page = 0
        val movie = Movie("", "", "", "", "", 0, false,0.0, 0.0)
        val movies = mutableListOf<Movie>()
        movies.add(movie)
        whenever(movieDao.getMovies()).thenReturn(Single.just(movies))
        moviesRepository.loadMoviesFromDisk(category, page)
        verify(movieDao, times(1)).getMovies()
        verifyNoMoreInteractions(movieDao)
    }

}