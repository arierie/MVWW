package id.arieridwan.mvww.data.repositoryimpl

import id.arieridwan.mvww.BuildConfig
import id.arieridwan.mvww.data.mapper.MovieMapper
import id.arieridwan.mvww.data.local.database.MovieDB
import id.arieridwan.mvww.data.remote.service.ApiService
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.presentation.entity.MovieViewParam
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MoviesRepositoryImpl(
    private val apiService: ApiService,
    private val movieDataBase: MovieDB
) : MoviesRepository {

    override fun loadMovies(category: String, page: Int): Observable<List<MovieViewParam>> {
        return getMoviesFromNetwork(category, page)
    }

    private fun getMoviesFromNetwork(category: String, page: Int): Observable<List<MovieViewParam>> {
        return apiService.getMovies(category, BuildConfig.ApiKey, page)
            .map { MovieMapper.toMovieViewParamsFromResponse(it.movies) }
            .doOnNext { movieListResponse ->
                movieDataBase.movieDao().saveMovies(MovieMapper.toMovies(movieListResponse))
            }
    }

    private fun getMoviesFromLocal(): Observable<List<MovieViewParam>> {
        return movieDataBase.movieDao().getMovies()
            .map { movies ->
                MovieMapper.toMovieViewParamsFromMovie(movies)
            }
            .toObservable()
    }

    private fun loadMoviesDong(category: String, page: Int) =
        Observable.mergeDelayError(
            getMoviesFromNetwork(category, page).subscribeOn(Schedulers.io()),
            getMoviesFromLocal().subscribeOn(Schedulers.io())
        )

}
