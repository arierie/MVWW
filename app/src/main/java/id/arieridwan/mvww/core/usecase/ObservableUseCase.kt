package id.arieridwan.mvww.core.usecase

import id.arieridwan.mvww.core.custom.schedulers.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo

/**
 * Created by arieridwan on 02/01/19.
 */

abstract class ObservableUseCase<T, in Params>: BaseUseCase() {

    internal abstract fun buildUseCaseObservable(params: Params): Observable<T>

    fun execute(
        onNext: ((t: T) -> Unit),
        onError: ((t: Throwable) -> Unit),
        onComplete: (() -> Unit),
        params: Params,
        schedulerProvider: BaseSchedulerProvider
    ) {
        disposeLast()
        lastDisposable = buildUseCaseObservable(params)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe(onNext, onError, onComplete)
            .addTo(compositeDisposable)
    }

}