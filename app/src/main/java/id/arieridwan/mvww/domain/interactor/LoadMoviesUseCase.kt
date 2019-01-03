package id.arieridwan.mvww.domain.interactor

import id.arieridwan.mvww.core.ObservableUseCase
import id.arieridwan.mvww.presentation.entity.MovieViewParam
import id.arieridwan.mvww.domain.repository.MoviesRepository
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

class LoadMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository)
    : ObservableUseCase<List<MovieViewParam>, LoadMoviesUseCase.Params>() {

    override fun buildUseCaseObservable(params: Params): Observable<List<MovieViewParam>> {
        return with(params) {
            moviesRepository.loadMovies(category, page)
        }
    }

    data class Params(
        val category: String,
        val page: Int
    )

}