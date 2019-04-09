package id.arieridwan.mvww.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import id.arieridwan.mvww.core.state.Async
import id.arieridwan.mvww.domain.usecase.LoadMoviesUseCase
import id.arieridwan.mvww.core.ui.BaseViewModel
import id.arieridwan.mvww.presentation.model.MovieUiModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by arieridwan on 26/12/18.
 */

class HomeViewModel @Inject constructor(private val loadMoviesUseCase: LoadMoviesUseCase)
    : BaseViewModel(loadMoviesUseCase) {

    val loadMoviesLiveData: MutableLiveData<Async<List<MovieUiModel>>> = MutableLiveData()

    private val compositeDisposable = CompositeDisposable()

    fun loadMovies(param: LoadMoviesUseCase.Params) {
        loadMoviesLiveData.value = Async.Loading()
        loadMoviesUseCase.buildUseCaseObservable(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    loadMoviesLiveData.value = Async.Success(it)
                }, {
                    loadMoviesLiveData.value = Async.Fail(it)
                }, {
                    loadMoviesLiveData.value = Async.Complete()
                })
                .addTo(compositeDisposable)

    }

}