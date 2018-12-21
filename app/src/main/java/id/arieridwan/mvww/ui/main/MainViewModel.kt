package id.arieridwan.mvww.ui.main

import android.arch.lifecycle.LiveData
import id.arieridwan.mvww.ui.base.BaseViewModel
import id.arieridwan.mvww.core.DataRequestState
import id.arieridwan.mvww.core.SingleLiveEvent
import id.arieridwan.mvww.core.State
import id.arieridwan.mvww.domain.entity.MovieViewParam
import id.arieridwan.mvww.domain.interactor.LoadMoviesUseCase
import javax.inject.Inject

/**
 * Created by arieridwan on 20/12/18.
 */

class MainViewModel @Inject constructor(private val loadMoviesUseCase: LoadMoviesUseCase)
    : BaseViewModel(loadMoviesUseCase) {

    private val _showMovies: SingleLiveEvent<DataRequestState<List<MovieViewParam>>> = SingleLiveEvent()
    val showMovies: LiveData<DataRequestState<List<MovieViewParam>>>
        get() = _showMovies

    fun loadMovies(category: String, page: Int) {
        loadMoviesUseCase.execute(
            onSuccess = {
                _showMovies.value = DataRequestState(State.SUCCEEDED, it)
            },
            onError = {
                _showMovies.value = DataRequestState(State.FAILED, null, it.message)
            },
            onFinished = {
                _showMovies.value = DataRequestState(State.COMPLETED)
            },
            params = LoadMoviesUseCase.Params(category, page)
        )
    }

}