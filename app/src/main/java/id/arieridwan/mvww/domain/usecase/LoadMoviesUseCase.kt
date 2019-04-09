package id.arieridwan.mvww.domain.usecase

import id.arieridwan.mvww.core.usecase.BaseUseCase
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

class LoadMoviesUseCase @Inject constructor (private val moviesRepository: MoviesRepository)
    : BaseUseCase() {

    fun buildUseCaseObservable(params: Params): Observable<List<MovieUiModel>> {
        return with(params) {
            moviesRepository.loadMoviesFromNetwork(category, page)
        }
    }

    data class Params(
        val category: String,
        val page: Int
    )

    fun loadMovies(params: Params): Observable<List<MovieUiModel>> {
        return moviesRepository.loadMoviesFromNetwork(params.category, params.page)
    }

}