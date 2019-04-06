package id.arieridwan.mvww.presentation.ui.home

import id.arieridwan.mvww.core.custom.SingleLiveEvent
import id.arieridwan.mvww.core.state.AsyncRequest
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.core.ui.BaseViewModel
import id.arieridwan.mvww.presentation.model.MovieUiModel
import javax.inject.Inject

/**
 * Created by arieridwan on 26/12/18.
 */

class HomeViewModel @Inject constructor(private val loadMoviesUseCase: LoadMoviesUseCase)
    : BaseViewModel(loadMoviesUseCase) {

    val loadMoviesLiveData: SingleLiveEvent<AsyncRequest<MovieUiModel>> = SingleLiveEvent()

    fun loadMovies(param: LoadMoviesUseCase.Params) {

    }

}