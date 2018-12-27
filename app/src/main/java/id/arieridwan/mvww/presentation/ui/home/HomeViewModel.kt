package id.arieridwan.mvww.presentation.ui.home

import android.arch.lifecycle.LiveData
import id.arieridwan.mvww.core.SingleLiveEvent
import id.arieridwan.mvww.presentation.entity.MovieViewParam
import id.arieridwan.mvww.domain.interactor.LoadMoviesUseCase
import id.arieridwan.mvww.presentation.ui.base.BaseViewModel
import id.arieridwan.mvww.presentation.ui.base.state.DataRequestState
import id.arieridwan.mvww.presentation.ui.base.state.ScreenState
import id.arieridwan.mvww.presentation.ui.base.state.State
import javax.inject.Inject

/**
 * Created by arieridwan on 26/12/18.
 */

class HomeViewModel @Inject constructor(private val loadMoviesUseCase: LoadMoviesUseCase)
    : BaseViewModel(loadMoviesUseCase) {

    private val _showMovies: SingleLiveEvent<DataRequestState<List<MovieViewParam>>> = SingleLiveEvent()
    val showMovies: LiveData<DataRequestState<List<MovieViewParam>>>
        get() = _showMovies

    private val _screenChange: SingleLiveEvent<ScreenState> = SingleLiveEvent()
    val screenChange: LiveData<ScreenState>
        get() = _screenChange

    fun loadMovies(category: String, page: Int) {
        loadMoviesUseCase.execute(
            onSubscribe = {
                _screenChange.postValue(ScreenState.LOADING) // use postValue on background thread
            },
            onSuccess = {
                _showMovies.value = DataRequestState(State.SUCCEEDED, it)
            },
            onError = {
                _showMovies.value = DataRequestState(
                    State.FAILED,
                    null,
                    it.message
                )
            },
            onFinished = {
                _screenChange.value = ScreenState.AVAILABLE // currently, we don't have an empty state, it will always available
                _showMovies.value = DataRequestState(State.COMPLETED)
            },
            params = LoadMoviesUseCase.Params(category, page)
        )
    }

}