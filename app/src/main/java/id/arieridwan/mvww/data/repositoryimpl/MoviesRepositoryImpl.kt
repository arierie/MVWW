package id.arieridwan.mvww.data.repositoryimpl

import id.arieridwan.mvww.data.local.database.MovieDB
import id.arieridwan.mvww.data.remote.entity.MovieResponse
import id.arieridwan.mvww.data.remote.service.ApiService
import id.arieridwan.mvww.domain.repository.MoviesRepository
import io.reactivex.Observable

class MoviesRepositoryImpl(
    private val apiService: ApiService,
    private val movieDataBase: MovieDB
) : MoviesRepository {

    override fun loadMovies(category: String, page: Int): Observable<List<MovieResponse>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}