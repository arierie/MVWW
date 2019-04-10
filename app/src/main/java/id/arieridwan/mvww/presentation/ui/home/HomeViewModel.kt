package id.arieridwan.mvww.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import id.arieridwan.mvww.core.reactivex.observer.SingleObserver
import id.arieridwan.mvww.core.reactivex.schedulers.BaseSchedulerProvider
import id.arieridwan.mvww.core.state.Async
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.core.ui.BaseViewModel
import id.arieridwan.mvww.presentation.model.MovieUiModel
import javax.inject.Inject

/**
 * Created by arieridwan on 26/12/18.
 */

class HomeViewModel @Inject constructor(private val loadMoviesUseCase: LoadMoviesUseCase,
                                        private val schedulerProvider: BaseSchedulerProvider)
    : BaseViewModel(loadMoviesUseCase) {

    val loadMoviesLiveData: MutableLiveData<Async<List<MovieUiModel>>> = MutableLiveData()

    fun loadMovies(param: LoadMoviesUseCase.Params) {
        loadMoviesLiveData.value = Async.Loading()
        loadMoviesUseCase.execute(SingleObserver(doOnSuccess = {
            loadMoviesLiveData.value = Async.Success(it)
        }, doOnError = {
            loadMoviesLiveData.value = Async.Fail(it)
        }), param, schedulerProvider)
    }

}