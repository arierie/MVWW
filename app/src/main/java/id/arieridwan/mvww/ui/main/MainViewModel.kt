package id.arieridwan.mvww.ui.main

import android.arch.lifecycle.LiveData
import id.arieridwan.mvww.ui.base.BaseViewModel
import id.arieridwan.mvww.core.DataRequestState
import id.arieridwan.mvww.core.SingleLiveEvent
import id.arieridwan.mvww.core.State
import id.arieridwan.mvww.gateway.entity.MovieListResponse
import id.arieridwan.mvww.domain.interactor.MoviesUseCase
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

class MainViewModel @Inject constructor(private val moviesUseCase: MoviesUseCase): BaseViewModel(moviesUseCase) {

    private val _showMovies: SingleLiveEvent<DataRequestState<MovieListResponse>> = SingleLiveEvent()
    val showMovies: LiveData<DataRequestState<MovieListResponse>>
        get() = _showMovies

    fun loadMovies(category: String, page: Int) {
        moviesUseCase.execute(
            onSuccess = {
                _showMovies.value = DataRequestState(State.SUCCEEDED, it)
            },
            onError = {
                _showMovies.value = DataRequestState(State.FAILED, null, it.message)
            },
            onFinished = {
                _showMovies.value = DataRequestState(State.COMPLETED)
            },
            params = MoviesUseCase.Params(category, page)
        )
    }

}