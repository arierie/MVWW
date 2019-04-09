package id.arieridwan.mvww.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import id.arieridwan.mvww.core.custom.schedulers.BaseSchedulerProvider
import id.arieridwan.mvww.core.state.Async
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.core.ui.BaseViewModel
import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * Created by arieridwan on 26/12/18.
 */

class HomeViewModel @Inject constructor(private val loadMoviesUseCase: LoadMoviesUseCase,
                                        private val schedulerProvider: BaseSchedulerProvider)
    : BaseViewModel(loadMoviesUseCase) {

    val loadMoviesLiveData: MutableLiveData<Async<List<MovieUiModel>>> = MutableLiveData()
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun loadMovies(param: LoadMoviesUseCase.Params) {
        loadMoviesLiveData.value = Async.Loading()
//        // Proper way
//        loadMoviesUseCase.execute(onNext = {
//            loadMoviesLiveData.value = Async.Success(it)
//        }, onError = {
//            loadMoviesLiveData.value = Async.Fail(it)
//        }, onComplete = {
//            loadMoviesLiveData.value = Async.Complete()
//        }, params = param, schedulerProvider = schedulerProvider)

        // Alternative
        loadMoviesUseCase.loadMovies(param)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe({
                    loadMoviesLiveData.value = Async.Success(it)
                }, {}, {})
                .addTo(compositeDisposable)
    }

}