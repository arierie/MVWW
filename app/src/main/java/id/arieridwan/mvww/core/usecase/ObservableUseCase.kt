package id.arieridwan.mvww.core.usecase

import id.arieridwan.mvww.core.reactivex.scheduler.BaseSchedulerProvider
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import io.reactivex.rxkotlin.addTo

/**
 * Created by arieridwan on 02/01/19.
 */

abstract class ObservableUseCase<T, in Params>: BaseUseCase() {

    internal abstract fun buildUseCaseObservable(params: Params): Observable<T>

    internal fun execute(observer: DisposableObserver<T>,
                         params: Params,
                         schedulerProvider: BaseSchedulerProvider) {
        disposeLast()
        lastDisposable = buildUseCaseObservable(params)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(observer)
                .addTo(compositeDisposable)
    }

}