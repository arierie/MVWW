package id.arieridwan.mvww.data.repositoryimpl

import id.arieridwan.mvww.data.disk.dao.MovieDao
import id.arieridwan.mvww.data.network.service.ApiService
import id.arieridwan.mvww.domain.mapper.MovieMapper
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.presentation.entity.MovieUiModel
import io.reactivex.Observable

class MoviesRepositoryImpl(private val apiKey: String,
    private val apiService: ApiService,
    private val movieDao: MovieDao
) : MoviesRepository {

    override fun loadMoviesFromDisk(category: String, page: Int): Observable<List<MovieUiModel>> {
        return movieDao.getMovies().map { MovieMapper.fromListOfEntityToListOfUi(it) }.toObservable()
    }

    override fun loadMoviesFromNetwork(category: String, page: Int): Observable<List<MovieUiModel>> {
        return apiService.getMovies(category, apiKey, page)
                .map { MovieMapper.fromListOfResponseToListOfUi(it.movies)}
    }

}