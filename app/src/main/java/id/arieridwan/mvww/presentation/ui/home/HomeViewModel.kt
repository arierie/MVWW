package id.arieridwan.mvww.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import id.arieridwan.mvww.core.custom.schedulers.BaseSchedulerProvider
import id.arieridwan.mvww.core.state.Async
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.core.ui.BaseViewModel
import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.observers.DisposableObserver
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
        loadMoviesUseCase.execute(ObservableDisposableObserver(onSuccess = {
            loadMoviesLiveData.value = Async.Success(it)
        }, onFail = {
            loadMoviesLiveData.value = Async.Fail(it)
        }, onFinish = {
            loadMoviesLiveData.value = Async.Complete()
        }), param, schedulerProvider)
    }

    class ObservableDisposableObserver<T>(val onSuccess: ((t: T) -> Unit),
                                          val onFail: ((t: Throwable) -> Unit),
                                          val onFinish: (() -> Unit)) : DisposableObserver<T>() {

        override fun onComplete() {
            onFinish()
        }

        override fun onNext(it: T) {
            onSuccess(it)
        }

        override fun onError(e: Throwable) {
            onFail(e)
        }

    }

}