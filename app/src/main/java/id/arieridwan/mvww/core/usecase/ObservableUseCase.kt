package id.arieridwan.mvww.core.usecase

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

/**
 * Created by arieridwan on 02/01/19.
 */

abstract class ObservableUseCase<T, in Params>: BaseUseCase() {

    internal abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(
        onNext: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onComplete: (() -> Unit),
        params: Params
    ) {
        disposeLast()
        lastDisposable = buildUseCaseObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext, onError, onComplete)
                .addTo(compositeDisposable)
    }


}