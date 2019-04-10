package id.arieridwan.mvww.domain.usecase

import id.arieridwan.mvww.core.usecase.SingleUseCase
import id.arieridwan.mvww.domain.repository.MoviesRepository
import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

class LoadMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository)
    : SingleUseCase<List<MovieUiModel>, LoadMoviesUseCase.Params>() {

    override fun buildUseCaseSingle(params: Params): Single<List<MovieUiModel>> {
        return with(params) {
            moviesRepository.loadMoviesFromNetwork(category, page)
        }
    }

    data class Params(val category: String, val page: Int)

}