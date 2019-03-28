package id.arieridwan.mvww.domain.usecase

import id.arieridwan.mvww.core.usecase.ObservableUseCase
import id.arieridwan.mvww.data.remote.entity.MovieResponse
import id.arieridwan.mvww.domain.repository.MoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

class LoadMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository)
    : ObservableUseCase<List<MovieResponse>, LoadMoviesUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<List<MovieResponse>> {
        return with(params) {
            moviesRepository.loadMovies(category, page)
        }
    }

    data class Params(
        val category: String,
        val page: Int
    )

}